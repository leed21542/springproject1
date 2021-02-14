package com.example.springproject1.controller.api;

import com.example.springproject1.controller.CrudController;
import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.OrderGroup;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.OrderGroupApiRequest;
import com.example.springproject1.model.network.response.OrderGroupApiResponse;
import com.example.springproject1.service.OrderGroupApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}
