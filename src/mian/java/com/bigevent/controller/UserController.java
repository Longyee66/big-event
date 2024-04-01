package com.bigevent.controller;

import com.bigevent.pojo.Result;
import com.bigevent.pojo.User;
import com.bigevent.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    @ApiOperation("用户注册接口")
    public Result register(String username, String password) {
        User user = userService.getByUserName(username);
        if (user!=null){
            return Result.error("该用户已存在");
        }
        userService.addUser(username,password);
        return Result.success();
    }
}
