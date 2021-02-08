package com.example.springproject1.service;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.AdminUser;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.AdminUserRequest;
import com.example.springproject1.model.network.response.AdminUserResponse;
import com.example.springproject1.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserRequest, AdminUserResponse> {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserResponse> create(Header<AdminUserRequest> request) {

        AdminUserRequest body = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .registeredAt(LocalDateTime.now())
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserResponse> read(Long id) {

        return adminUserRepository.findById(id)
                .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserResponse> update(Header<AdminUserRequest> request) {

        AdminUserRequest body = request.getData();

        return adminUserRepository.findById(body.getId())
                .map(adminUser -> {
                    adminUser.setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole());
                    return adminUser;
                })
                .map(newAdminUser -> adminUserRepository.save(newAdminUser))
                .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return adminUserRepository.findById(id)
                .map(adminUser -> {
                    adminUserRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<AdminUserResponse> response(AdminUser adminUser){

        AdminUserResponse body = AdminUserResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .registeredAt(adminUser.getRegisteredAt())
                .build();
        return Header.OK(body);
    }
}
