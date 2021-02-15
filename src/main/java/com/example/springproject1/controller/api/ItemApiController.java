package com.example.springproject1.controller.api;

import com.example.springproject1.controller.CrudController;
import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.Item;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.ItemApiRequest;
import com.example.springproject1.model.network.response.ItemApiResponse;
import com.example.springproject1.model.network.response.UserApiResponse;
import com.example.springproject1.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @GetMapping("")
    public Header<List<ItemApiResponse>> search(@PageableDefault(sort="id",direction = Sort.Direction.ASC,size=15) Pageable pageable){
        log.info("{}",pageable);
        return itemApiLogicService.search(pageable);
    }
}
