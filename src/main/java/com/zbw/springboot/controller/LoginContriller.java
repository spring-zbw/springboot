package com.zbw.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by 郑博文 on 2019/11/13.
 */
@RestController
@RequestMapping("/api/login/")
public class LoginContriller {

    @PostMapping("enter")
    public HashMap<String,Object> updateOutCashPassword(
             @RequestParam("user") String user,
            @RequestParam("password") String password
    ){
        HashMap<String,Object> hashMap=new HashMap<>();
        if(user.equals("zbw")&&password.equals("123")){
            hashMap.put("status",200);
            hashMap.put("message","登录成功");
        }
        return hashMap;
    }

}
