package com.example.springproject1.Repository;


import com.example.springproject1.Springproject1ApplicationTests;
import com.example.springproject1.model.entity.Item;
import com.example.springproject1.repository.ItemRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends Springproject1ApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    public void create(){
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("삼성 노트북");

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assert.assertTrue(item.isPresent());

        item.ifPresent(i -> {
            System.out.println(i);
        });
    }
}
