package com.bigevent.controller;

import com.bigevent.pojo.JwtProperties;
import com.bigevent.pojo.Result;
import com.bigevent.pojo.User;
import com.bigevent.service.UserService;
import com.bigevent.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@Validated
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/register")
    @ApiOperation("用户注册接口")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        Map map = new HashMap<>();
        map.put("username", username);
        User user = userService.getByUser(map);
        if (user != null) {
            return Result.error("该用户已存在");
        }
        userService.addUser(username, password);
        return Result.success();
    }

    @PostMapping("login")
    @ApiOperation("登录")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.login(username, password);
        if (user==null) {
            return Result.error("用户名或密码错误");
        }
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        log.info("生成token");
        return Result.success(token);
    }
}
