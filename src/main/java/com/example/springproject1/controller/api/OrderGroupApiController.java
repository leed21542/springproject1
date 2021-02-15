package com.example.springproject1.controller.api;

import com.example.springproject1.controller.CrudController;
import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.OrderGroup;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.OrderGroupApiRequest;
import com.example.springproject1.model.network.response.OrderGroupApiResponse;
import com.example.springproject1.model.network.response.PartnerResponse;
import com.example.springproject1.repository.OrderGroupRepository;
import com.example.springproject1.service.BaseService;
import com.example.springproject1.service.OrderGroupApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @GetMapping("")
    public Header<List<OrderGroupApiResponse>> search(@PageableDefault(sort="id",direction = Sort.Direction.ASC,size=15) Pageable pageable){
        log.info("{}",pageable);
        return orderGroupApiLogicService.search(pageable);
    }
}
