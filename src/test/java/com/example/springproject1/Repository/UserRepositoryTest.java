package com.example.springproject1.Repository;

import com.example.springproject1.Springproject1ApplicationTests;
import com.example.springproject1.model.entity.Item;
import com.example.springproject1.model.entity.User;
import com.example.springproject1.repository.UserRepository;
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
        User user = new User();
        user.setAccount("TestUser02");
        user.setEmail("leed215422@naver.com");
        user.setPhoneNumber("010-0111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin2");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);
    }

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findByAccount("TestUser02"); // Optional로 return 시켜줘야 한다.

        user.ifPresent(selectUser ->{ //Optional로 값을 받으면 값이 있으면 실행시키다. (없을수도 있기 때문)
            selectUser.getOrderDetailList().forEach(detail->{
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
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
