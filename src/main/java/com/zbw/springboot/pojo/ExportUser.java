package com.zbw.springboot.pojo;

/**
 * Created by 郑博文 on 2019/11/25.
 */
public class ExportUser {
    private Integer id;
    private String userName;
    private String mobile;
    private String address;
    private String registerDate;
    private String  wechatCredentials;

    public String getWechatCredentials() {
        return wechatCredentials;
    }

    public void setWechatCredentials(String wechatCredentials) {
        this.wechatCredentials = wechatCredentials;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
