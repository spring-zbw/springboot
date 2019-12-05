package com.zbw.springboot.util;

/**
 * Created by 郑博文 on 2019/12/4.
 */
public class ApplicationType {
//    JSON("application/json"), XML("application/xml"), TEXT("text/xml"), FORM("application/x-www-form-urlencoded");

    private String type;

    private ApplicationType(String type) {
        this.type = type;
    }

    public String val() {
        return type;
    }

}
