package com.bigevent.controller;

import com.bigevent.pojo.JwtProperties;
import com.bigevent.pojo.Result;
import com.bigevent.pojo.User;
import com.bigevent.pojo.dto.Password;
import com.bigevent.pojo.dto.UserDTO;
import com.bigevent.service.UserService;
import com.bigevent.utils.JwtUtil;
import com.bigevent.utils.ThreadLocalUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("admin/user")
@Api(tags = "用户相关接口")
@Validated
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.login(username, password);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        stringRedisTemplate.opsForValue().set("token",token,1, TimeUnit.DAYS);//将token存入redis中
        return Result.success(token);
    }

    @GetMapping("/userInfo")
    @ApiOperation("获取用户详细信息")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization")String token*/) {
       /* log.info("请求头中的token:{}", token);
        //解析token，获取当前用户的用户名
        Map<String, Object> claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        String username = (String) claims.get("username");*/
        String username = ThreadLocalUtils.getCurrentUserName();
        User user = userService.getUserInfo(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    @ApiOperation("更新用户基本信息")
    public Result update(@RequestBody @Validated UserDTO userDTO){
        userService.update(userDTO);
        return Result.success();
    }
    @PatchMapping("/updateAvatar")
    @ApiOperation("更新用户头像")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvarter(avatarUrl);
        return Result.success();
    }
    @PatchMapping("updatePwd")
    @ApiOperation("更新用户密码")
    public Result updatePwd(@RequestBody Password password){
        if (password.getOid_pwd().isEmpty()||password.getNew_pwd().isEmpty()||password.getRe_pwd().isEmpty()){
            return Result.error("密码不能为空！！！");
        }
        log.info("password:{}",password);
        User user=userService.getUserInfo(ThreadLocalUtils.getCurrentUserName());
        if (!DigestUtils.md5DigestAsHex(password.getOid_pwd().getBytes()).equals(user.getPassword())){
            return Result.error("原密码输入有误！！！");
        }
        if (!password.getNew_pwd().equals(password.getRe_pwd())){
            return Result.error("新密码不一致！！！");
        }
        userService.updatePwd(password.getNew_pwd());
        stringRedisTemplate.delete("token");
        return Result.success();
    }
}
