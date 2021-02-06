package com.example.springproject1.controller;

import com.example.springproject1.model.SearchParam;
import com.example.springproject1.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController //이 곳은 컨트롤러로 이용할 거야
@RequestMapping("/api") // Localhost:808/api 까지 주소가 매핑된다.
public class GetController {

    // Localhost:8080/api/getMethod를 읽으면 이 함수가 실행된다.
    @RequestMapping(method = RequestMethod.GET,path="/getMethod")
    public String getRequest(){

        return "Hi getMethod";
    }

    // Localhost:8080/api/getParameter?id=1234&password=abcd 로 매핑(RequestMapping과 다르게 method 지정 불필요)
    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id,@RequestParam String password){ //RequestParam은 생략 가능!!
        System.out.println("id : " +id);
        System.out.println("password : " +password);

        return id + password;
    }

    //Localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){ //객체를 통해서 파라미터를 받는다
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam; //json 형태로 출력된다 {"account":"". "email":"", "page":0}
    }

    @GetMapping("/header")
    public Header getHeader(){

        // {"resultCode : "OK", "description : OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }

}
