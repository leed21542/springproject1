package com.example.springproject1.service;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.Category;
import com.example.springproject1.model.entity.User;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.Pagination;
import com.example.springproject1.model.network.request.CategoryRequest;
import com.example.springproject1.model.network.response.CategoryResponse;
import com.example.springproject1.model.network.response.UserApiResponse;
import com.example.springproject1.repository.CategoryRepository;
import com.example.springproject1.repository.PartnerRepository;
import org.hibernate.hql.spi.id.cte.CteValuesListBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryApiLogicService implements CrudInterface<CategoryRequest, CategoryResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<CategoryResponse> create(Header<CategoryRequest> request) {

        CategoryRequest body = request.getData();

        Category category = Category.builder()
                .type(body.getType())
                .title(body.getTitle())
                .build();

        Category newCategory = categoryRepository.save(category);
        return Header.OK(response(newCategory));
    }

    @Override
    public Header<CategoryResponse> read(Long id) {


        return categoryRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryResponse> update(Header<CategoryRequest> request) {
        CategoryRequest body = request.getData();

        return categoryRepository.findByType(body.getType())
                .map(category -> {
                    category.setType(body.getType())
                            .setTitle(body.getTitle());
                    return category;
                })
                .map(newCategory -> categoryRepository.save(newCategory))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public CategoryResponse response(Category category){

        CategoryResponse body = CategoryResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .build();
        return body;
    }

    public Header<List<CategoryResponse>> search(Pageable pageable) {

        Page<Category> categories = categoryRepository.findAll(pageable);

        List<CategoryResponse> categoryResponseList = categories.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder() //페이지 정보를 저장한다.
                .totalPages(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .currentPage(categories.getNumber())
                .currentElements(categories.getNumberOfElements())
                .build();

        return Header.OK(categoryResponseList,pagination);
    }
}
