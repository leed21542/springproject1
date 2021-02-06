package com.example.springproject1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.criterion.Order;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;


    // Item N : 1 Partner
    @ManyToOne
    private Partner partner; //외래키

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetailList;

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
