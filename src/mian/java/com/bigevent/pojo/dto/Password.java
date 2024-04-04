package com.bigevent.pojo.dto;

import lombok.Data;

@Data
public class Password {
    private String oid_pwd;//原密码
    private String new_pwd;//新密码
    private String re_pwd;//确认密码
}
