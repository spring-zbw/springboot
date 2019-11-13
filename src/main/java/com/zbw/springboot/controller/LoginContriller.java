package com.zbw.springboot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by 郑博文 on 2019/11/13.
 */
@RestController
@RequestMapping("/api/login/")
@CrossOrigin
public class LoginContriller {

    @PostMapping("enter")
    public HashMap<String,Object> updateOutCashPassword(String user,String password){
        HashMap<String,Object> hashMap=new HashMap<>();
        if(user.equals("zbw")&&password.equals("123")){
            hashMap.put("status",200);
            hashMap.put("message","登录成功");
        }
        return hashMap;
    }

}
