package com.example.springproject1.service;

import com.example.springproject1.ifs.CrudInterface;
import com.example.springproject1.model.entity.User;
import com.example.springproject1.model.enumclass.UserStatus;
import com.example.springproject1.model.network.Header;
import com.example.springproject1.model.network.request.UserApiRequest;
import com.example.springproject1.model.network.response.UserApiResponse;
import com.example.springproject1.repository.UserRepository;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. request data 가져오기
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return 해준다.
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성

        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById

        Optional<User> optional = userRepository.findById(id);

        return optional.map(user -> response(user)) // map : 다른 리턴 형식으로 바꿔준다.
                .orElseGet(()->Header.ERROR("데이터 없음")); // user가 없다면 뒤 실행
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. id를 이용해 user 데이터를 찾는다.
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user->
            // 3. update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
            // 4. userApiResponse

            //return user;
        )
                .map(user -> userRepository.save(user)) // update 실행
                .map(updateUser -> response(updateUser)) // userApuResponse
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        // 1. id를 통해 repository에서 user를 찾는다.
        Optional<User> optional = userRepository.findById(id);

        // 2. repsitory에서 delete 한다.

        return optional.map(user->{
            userRepository.delete(user);

            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음"));

        // 3. response return
    }



    private Header<UserApiResponse> response(User user){

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return

        return Header.OK(userApiResponse);
    }
}
