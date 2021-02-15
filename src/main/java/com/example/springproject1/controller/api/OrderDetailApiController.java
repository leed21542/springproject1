package com.example.springproject1.controller.api;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.OrderDetailRequest;
import com.example.springproject1.model.network.response.OrderDetailResponse;
import com.example.springproject1.model.network.response.UserApiResponse;
import com.example.springproject1.repository.OrderDetailRepository;
import com.example.springproject1.service.OrderDetailLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController implements CrudInterface<OrderDetailRequest, OrderDetailResponse> {

    @Autowired
    private OrderDetailLogicService orderDetailLogicService;

    @GetMapping("")
    public Header<List<OrderDetailResponse>> search(@PageableDefault(sort="id",direction = Sort.Direction.ASC,size=15) Pageable pageable){
        log.info("{}",pageable);
        return orderDetailLogicService.search(pageable);
    }

    @Override
    @PostMapping("")
    public Header<OrderDetailResponse> create(@RequestBody Header<OrderDetailRequest> request) {
        return orderDetailLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderDetailResponse> read(@PathVariable Long id) {
        return orderDetailLogicService.read(id);
    }


    @Override
    @PutMapping("")
    public Header<OrderDetailResponse> update(@RequestBody Header<OrderDetailRequest> request) {
        return orderDetailLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return orderDetailLogicService.delete(id);
    }
}
