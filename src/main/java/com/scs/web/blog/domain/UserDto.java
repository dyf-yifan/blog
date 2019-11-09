package com.scs.web.blog.domain;

/**
 * @ClassName UserDto
 * @Description 用户传输对象
 * @Author
 * @Date 2019/11/9 15:36
 * @Version 1.0
 **/
public class UserDto {
    private String mobile;
    private String password;
    public UserDto(String mobile, String password){
        this.mobile = mobile;
        this.password = password;
    }
    public UserDto(){

    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
