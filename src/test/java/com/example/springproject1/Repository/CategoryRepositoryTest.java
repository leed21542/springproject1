package com.example.springproject1.Repository;

import com.example.springproject1.Springproject1ApplicationTests;
import com.example.springproject1.model.entity.Category;
import com.example.springproject1.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends Springproject1ApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);
        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(),type);
        Assert.assertEquals(newCategory.getTitle(),title);
    }

    @Test
    public void read(){

        Optional<Category> optionalCategory = categoryRepository.findByType("COMPUTER");

        optionalCategory.ifPresent(c->{
            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());


        });
    }
}
