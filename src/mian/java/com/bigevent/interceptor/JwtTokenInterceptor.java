package com.bigevent.interceptor;


import com.bigevent.pojo.JwtProperties;
import com.bigevent.utils.JwtUtil;
import com.bigevent.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    拦截登录
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是否是动态资源
        if (!(handler instanceof HandlerMethod)){
            //不是controller中的方法,放行
            return true;
        }
        //1、从请求头中获取token
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        //2.从redis中获取token
        String redisToken=stringRedisTemplate.opsForValue().get("token");
        try {
            log.info("jwt校验：{}",token);
            if (redisToken==null||token.equals(redisToken)){
                throw new RuntimeException();
            }
            Map<String, Object> claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            String username = (String) claims.get("username");
            ThreadLocalUtils.setCurrentUserName(username);
            log.info("当前登录用户：{}",username);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtils.remove();
    }
}
