package com.example.springproject1.service;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.OrderDetail;
import com.example.springproject1.model.entity.User;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.Pagination;
import com.example.springproject1.model.network.request.OrderDetailRequest;
import com.example.springproject1.model.network.response.OrderDetailResponse;
import com.example.springproject1.model.network.response.UserApiResponse;
import com.example.springproject1.repository.ItemRepository;
import com.example.springproject1.repository.OrderDetailRepository;
import com.example.springproject1.repository.OrderGroupRepository;
import com.example.springproject1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailLogicService implements CrudInterface<OrderDetailRequest, OrderDetailResponse> {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;


    @Override
    public Header<OrderDetailResponse> create(Header<OrderDetailRequest> request) {

        OrderDetailRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(LocalDateTime.now())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .item(itemRepository.getOne(body.getItemId()))
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .build();

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        return Header.OK(response(newOrderDetail));
    }

    @Override
    public Header<OrderDetailResponse> read(Long id) {

        return orderDetailRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailResponse> update(Header<OrderDetailRequest> request) {

        OrderDetailRequest body = request.getData();

        return orderDetailRepository.findById(body.getId())
                .map(orderDetail -> {
                    orderDetail
                            .setStatus(body.getStatus())
                            .setArrivalDate(body.getArrivalDate())
                            .setQuantity(body.getQuantity())
                            .setTotalPrice(body.getTotalPrice())
                            .setItem(itemRepository.getOne(body.getItemId()))
                            .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()));
                    return orderDetail;
                })
                .map(newOrderDetail -> orderDetailRepository.save(newOrderDetail))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return orderDetailRepository.findById(id)
                .map(orderDetail -> {
                    orderDetailRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public OrderDetailResponse response(OrderDetail orderDetail){

        OrderDetailResponse body = OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(LocalDateTime.now())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .itemId(orderDetail.getItem().getId())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .build();

        return body;
    }

    public Header<List<OrderDetailResponse>> search(Pageable pageable) {

        Page<OrderDetail> orderDetails = orderDetailRepository.findAll(pageable);

        List<OrderDetailResponse> orderDetailResponseList = orderDetails.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder() //페이지 정보를 저장한다.
                .totalPages(orderDetails.getTotalPages())
                .totalElements(orderDetails.getTotalElements())
                .currentPage(orderDetails.getNumber())
                .currentElements(orderDetails.getNumberOfElements())
                .build();

        return Header.OK(orderDetailResponseList,pagination);
    }
}
