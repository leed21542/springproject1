package com.example.springproject1.service;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.Partner;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.PartnerRequest;
import com.example.springproject1.model.network.response.PartnerResponse;
import com.example.springproject1.repository.CategoryRepository;
import com.example.springproject1.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PartnerApiLogicService implements CrudInterface<PartnerRequest, PartnerResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Header<PartnerResponse> create(Header<PartnerRequest> request) {

        PartnerRequest body = request.getData();

        Partner partner = Partner.builder()
                .name(body.getName())
                .status(body.getStatus())
                .address(body.getAddress())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(LocalDateTime.now())
                .category(categoryRepository.getOne(body.getCategoryId()))
                .build();

        Partner newPartner = partnerRepository.save(partner);
        return response(newPartner);
    }

    @Override
    public Header<PartnerResponse> read(Long id) {

        return partnerRepository.findById(id)
                .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerResponse> update(Header<PartnerRequest> request) {

        PartnerRequest body = request.getData();

        return partnerRepository.findById(body.getId())
                .map(partner -> {
                    partner.setName(body.getName())
                            .setStatus(body.getStatus())
                            .setAddress(body.getAddress())
                            .setCallCenter(body.getCallCenter())
                            .setPartnerNumber(body.getPartnerNumber())
                            .setBusinessNumber(body.getBusinessNumber())
                            .setCeoName(body.getCeoName())
                            .setRegisteredAt(LocalDateTime.now());
                    return partner;
                })
                .map(newPartner -> partnerRepository.save(newPartner))
                .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return partnerRepository.findById(id)
                .map(partner -> {
                    partnerRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<PartnerResponse> response(Partner partner){

        PartnerResponse body = PartnerResponse.builder()
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return Header.OK(body);
    }
}
