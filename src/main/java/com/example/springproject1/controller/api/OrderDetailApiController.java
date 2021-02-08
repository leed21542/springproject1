package com.example.springproject1.controller.api;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.OrderDetailRequest;
import com.example.springproject1.model.network.response.OrderDetailResponse;
import com.example.springproject1.repository.OrderDetailRepository;
import com.example.springproject1.service.OrderDetailLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController implements CrudInterface<OrderDetailRequest, OrderDetailResponse> {

    @Autowired
    private OrderDetailLogicService orderDetailLogicService;

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
