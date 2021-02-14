package com.example.springproject1.service;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.Partner;
import com.example.springproject1.model.entity.User;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.Pagination;
import com.example.springproject1.model.network.request.PartnerRequest;
import com.example.springproject1.model.network.response.PartnerResponse;
import com.example.springproject1.model.network.response.UserApiResponse;
import com.example.springproject1.repository.CategoryRepository;
import com.example.springproject1.repository.PartnerRepository;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        return Header.OK(response(newPartner));
    }

    @Override
    public Header<PartnerResponse> read(Long id) {

        return partnerRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
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
                .map(Header::OK)
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

    public PartnerResponse response(Partner partner){

        PartnerResponse body = PartnerResponse.builder()
                .id(partner.getId())
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

        return body;
    }

    public Header<List<PartnerResponse>> search(Pageable pageable) {

        Page<Partner> partners = partnerRepository.findAll(pageable);

        List<PartnerResponse> partnerResponseList = partners.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder() //페이지 정보를 저장한다.
                .totalPages(partners.getTotalPages())
                .totalElements(partners.getTotalElements())
                .currentPage(partners.getNumber())
                .currentElements(partners.getNumberOfElements())
                .build();

        return Header.OK(partnerResponseList,pagination);
    }
}
