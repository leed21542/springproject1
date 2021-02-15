package com.example.springproject1.controller.api;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.CategoryRequest;
import com.example.springproject1.model.network.response.CategoryResponse;
import com.example.springproject1.model.network.response.UserApiResponse;
import com.example.springproject1.service.CategoryApiLogicService;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryApiController implements CrudInterface<CategoryRequest, CategoryResponse> {

    @Autowired
    private CategoryApiLogicService categoryApiLogicService;

    @GetMapping("")
    public Header<List<CategoryResponse>> search(@PageableDefault(sort="id",direction = Sort.Direction.ASC,size=15) Pageable pageable){
        log.info("{}",pageable);
        return categoryApiLogicService.search(pageable);
    }

    @Override
    @PostMapping("")
    public Header<CategoryResponse> create(@RequestBody Header<CategoryRequest> request) {
        return categoryApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<CategoryResponse> read(@PathVariable Long id) {
        return categoryApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<CategoryResponse> update(@RequestBody Header<CategoryRequest> request) {

        return categoryApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {

        return categoryApiLogicService.delete(id);
    }
}
