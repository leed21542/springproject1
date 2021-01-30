package com.example.springproject1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private Integer price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //LAZY = 지연로딩   , EAGER = 즉시로딩

    //LAZY = SELECT * FROM item where id = ?
    // -> orderDetailList의 get 메소드를 호츌하지 않으면 연관관계의 테이블을 SELECT하지 않겠다.
    // -> 1 : M 일 때 추천

    //EAGER = 조인이 발생한다.
    // -> get 메소드를 호출하지 않아도 모든 테이블과 조인을 통해 SELECT를 즉시 실행하겠다.
    // -> 1 : 1 로 매칭될 때 추천한다.
    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetailList;*/
}
