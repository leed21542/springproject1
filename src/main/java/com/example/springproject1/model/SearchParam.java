package com.example.springproject1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //Lombok (Getter, Setter 등 모든 필요요소 추가해준다.)
@AllArgsConstructor //모든 종류의 생성자 추가
public class SearchParam { //파라미터를 객체로 받기 위한 클래스

    private String account;
    private String email;
    private int page;
}
