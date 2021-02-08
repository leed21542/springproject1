package com.example.springproject1.service;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.Category;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.CategoryRequest;
import com.example.springproject1.model.network.response.CategoryResponse;
import com.example.springproject1.repository.CategoryRepository;
import com.example.springproject1.repository.PartnerRepository;
import org.hibernate.hql.spi.id.cte.CteValuesListBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return response(newCategory);
    }

    @Override
    public Header<CategoryResponse> read(Long id) {


        return categoryRepository.findById(id)
                .map(this::response)
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

    public Header<CategoryResponse> response(Category category){

        CategoryResponse body = CategoryResponse.builder()
                .type(category.getType())
                .title(category.getTitle())
                .build();
        return Header.OK(body);
    }
}
