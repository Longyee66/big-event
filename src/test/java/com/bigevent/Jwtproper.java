package com.bigevent;

import com.bigevent.pojo.JwtProperties;
import com.bigevent.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class Jwtproper {

//    @Test
//    public void createToken(){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("username","long");
//        map.put("userId","111");
//        String token= JwtUtil.createJWT("long",720000,map);
//        System.out.println(token);
//    }
//    @Test
//    public void preaseToken(){
//        Map<String, Object> claims = JwtUtil.parseJWT("long", "eyJhbGciOiJIUzI1NiJ9." +
//                "eyJleHAiOjE3MTIwNTYxNTIsInVzZXJJZCI6IjExMSIsInVzZXJuYW1lIjoibG9uZyJ9." +
//                "XXv7FLG-YSbcPRCp2JkYWj54qNJz4oJue_QCggsY4Gg");
//        System.out.println(claims.get("username"));
//    }
}
