package com.example.springproject1.controller.api;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.AdminUserRequest;
import com.example.springproject1.model.network.response.AdminUserResponse;
import com.example.springproject1.repository.AdminUserRepository;
import com.example.springproject1.service.AdminUserApiLogicService;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController implements CrudInterface<AdminUserRequest, AdminUserResponse> {

    @Autowired
    private AdminUserApiLogicService adminUserApiLogicService;

    @Override
    @PostMapping("")
    public Header<AdminUserResponse> create(@RequestBody Header<AdminUserRequest> request) {
        return adminUserApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<AdminUserResponse> read(@PathVariable Long id) {
        return adminUserApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<AdminUserResponse> update(@RequestBody Header<AdminUserRequest> request) {
        return adminUserApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return adminUserApiLogicService.delete(id);
    }
}
