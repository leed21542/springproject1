package com.example.springproject1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@ToString(exclude = {"user","item"}) //서로 상호참조하는 것을 풀어준다.
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private Long itemId;

    private Long orderGroupId;


    //N : 1
    //User 테이블과의 연관관계
    /*@ManyToOne
    private User user; // 연관 관계 설정시 객체의 이름으로 선언해줘야 한다.-> 알아서 user_id를 찾아간다.

    // N : 1계
    //Item 테이블과의 연관관계
    @ManyToOne
    private Item item;*/
}
