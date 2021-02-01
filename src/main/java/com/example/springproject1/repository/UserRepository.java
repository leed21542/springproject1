package com.example.springproject1.repository;

import com.example.springproject1.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //Jpa에서 이 문장에서 findBy까지 보고 select 문을 판별, 그 다음 오는 것이 Account 구나
    // select * from user where account = ?
    /*Optional<User> findByAccount(String account); // -> 쿼리 메소드

    Optional<User> findByEmail(String email);

    //여러 가지를 한번에 선택하겠다.
    Optional<User> findByAccountAndEmail(String account,String email);*/

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
