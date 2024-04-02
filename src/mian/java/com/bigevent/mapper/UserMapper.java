package com.bigevent.mapper;

import com.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {
    //查询用户
    User getByUser(Map map);
    @Insert("insert into user(username, password, create_time, update_time) values " +
            "(#{username},#{password},#{createTime},#{updateTime})")
    void insert(User user);

}
