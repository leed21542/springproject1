package com.example.springproject1.controller;

import com.example.springproject1.model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") //클래스에 대한 RequestMapping 하는 주소가 겹쳐도 상관없다. (but 메서드에 대해서는 문제 발생)
public class PostController {

    // Post 방식
    // http - post - body 에 data를 집어넣어 보내겠다. -> @RequestBody 사용
    //

    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){

        return searchParam; //Json 형태로 return
    }


}
