package com.example.springproject1.controller.api;

import com.example.springproject1.controller.CrudController;
import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.Item;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.ItemApiRequest;
import com.example.springproject1.model.network.response.ItemApiResponse;
import com.example.springproject1.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}
