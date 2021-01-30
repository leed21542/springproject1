package com.example.springproject1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User { //DB의 user엔티티와 이름이 동일해야 한다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber; //JAVA에서는 Camel Case(대문자로 띄어쓰기), DB에서는 Snake Case(_로 연결) -> 자동으로 매칭해준다.

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // 1 : N
    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "user") //order_detaiㅣ의 user와 연결시켜 주겠다.
    private List<OrderDetail> orderDetailList;
     */
}

