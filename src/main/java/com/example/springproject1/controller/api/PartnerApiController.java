package com.example.springproject1.controller.api;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.PartnerRequest;
import com.example.springproject1.model.network.response.PartnerResponse;
import com.example.springproject1.service.PartnerApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController implements CrudInterface<PartnerRequest, PartnerResponse> {

    @Autowired
    private PartnerApiLogicService partnerApiLogicService;

    @Override
    @PostMapping("")
    public Header<PartnerResponse> create(@RequestBody Header<PartnerRequest> request) {
        return partnerApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerResponse> read(@PathVariable Long id) {
        return partnerApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<PartnerResponse> update(@RequestBody Header<PartnerRequest> request) {
        return partnerApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return partnerApiLogicService.delete(id);
    }
}
