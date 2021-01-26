package com.example.springproject1.model;

import lombok.Data;

@Data
public class SearchParam { //파라미터를 객체로 받기 위한 클래스

    private String account;
    private String email;
    private int page;
}
