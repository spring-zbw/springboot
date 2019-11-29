package com.zbw.springboot.controller;

import com.zbw.springboot.pojo.ExportUser;
import com.zbw.springboot.pojo.InputCondition;
import com.zbw.springboot.service.TimerStatisticService;
import com.zbw.springboot.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 郑博文 on 2019/11/13.
 */
@RestController
@RequestMapping("/api/login/")
@CrossOrigin
public class LoginContriller {

     @Autowired
     private TimerStatisticService timerStatisticService;

    @PostMapping("enter")
    public HashMap<String,Object> updateOutCashPassword(String user,String password){
        HashMap<String,Object> hashMap=new HashMap<>();
        if(user.equals("zbw")&&password.equals("123")){
            hashMap.put("status",200);
            hashMap.put("message","登录成功");
        }
        return hashMap;
    }
    @GetMapping("export")
    public void excelDownload(HttpServletResponse response,InputCondition inputCondition) throws IOException {

        List<ExportUser> userList= timerStatisticService.getExportUser(inputCondition);
        String sheetName = inputCondition.getTextName();
        String fileName = inputCondition.getTextName()+".xls";

        ExcelUtil.exportExcel(response, userList, sheetName, fileName, 18);

    }

}
