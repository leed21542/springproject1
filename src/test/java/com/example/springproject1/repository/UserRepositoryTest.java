package com.example.springproject1.repository;

import com.example.springproject1.Springproject1ApplicationTests;
import com.example.springproject1.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends Springproject1ApplicationTests {

    //디자인 패턴
    // 의존성의 주입 : 자동으로 @Autowired를 찾아 의존성을 주입해준다. 객체를 생성할 필요 없다.
    // Dependency Injection(DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test06";
        String password = "Test04";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);


        //@Builder 어노테이션 사용 !! -> 생성자 생성시 파라미터에 몇개의 값이 들어갈지 모르기 때문에 Builder를 사용하여
        // 간단하게 객체를 생성할 수 있다.
        User u  = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        //user.setEmail().setPhoneNumber().setStatus(); //Accessors 어노테이션 사용하여 채이팅식으로 객체를 업데이트 시킨다.
        System.out.println(user);
            user.getOrderGroupList().forEach(a -> {
                System.out.println("-------------주문묶음-------------");
                System.out.println("수령인 : " + a.getRevName());
                System.out.println("수령지 : " + a.getRevAddress());
                System.out.println("총금액 : " + a.getTotalPrice());
                System.out.println("총수량 : " + a.getTotalQuantity());

                System.out.println("-------------주문상세-------------");

                a.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : "+orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일 : " + orderDetail.getArrivalDate());


                });
            });
        Assert.assertNotNull(user);

    }

    public void update(){

        Optional<User> user = userRepository.findById(2L); // Optional로 return 시켜줘야 한다.

        user.ifPresent(selectUser ->{ //Optional로 값을 받으면 값이 있으면 실행시키다. (없을수도 있기 때문)
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }


    @Transactional // 쿼리는 실행되지만 데이터는 그대로 남아있게 한다. (RollBack 기능)
    public void delete(){

        Optional<User> user = userRepository.findById(2L); // Optional로 return 시켜줘야 한다.

        Assert.assertTrue(user.isPresent()); //반드시 값이 있어야 통과된다.

        user.ifPresent(selectUser ->{ //Optional로 값을 받으면 값이 있으면 실행시키다. (없을수도 있기 때문)
            userRepository.delete(selectUser);
        });

        Optional<User> deleteuser = userRepository.findById(2L); // Optional로 return 시켜줘야 한다.

        Assert.assertFalse(deleteuser.isPresent()); // 반드시 값이 없어야 통과된다.
    }

}
