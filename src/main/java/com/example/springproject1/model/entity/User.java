package com.example.springproject1.model.entity;

import com.example.springproject1.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder // 생성자에 몇 개의 파라미터 값이 들어올지 모르니 builder 패턴을 사용하여 간편하게 객체 생성이 가능하다.
@Accessors(chain = true) // ex) update 시 user.setEmail().setPhoneNumber().setStatus()와 같이 계속해서
// 채이닝하여 값을 변경시킬 수 있게 해준다.
public class User { //DB의 user엔티티와 이름이 동일해야 한다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status; //ENUM 으로 표현해준다.

    private String email;

    private String phoneNumber; //JAVA에서는 Camel Case(대문자로 띄어쓰기), DB에서는 Snake Case(_로 연결) -> 자동으로 매칭해준다.

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

    //User 1 : N OrderGroup
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<OrderGroup> orderGroupList;

    // 1 : N
    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "user") //order_detaiㅣ의 user와 연결시켜 주겠다.
    private List<OrderDetail> orderDetailList;
     */
}

