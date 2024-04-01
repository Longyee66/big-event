package com.bigevent.mapper;

import com.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {
    //查询用户
    @Select("select * from user where username=#{username}")
    User getByUserName(String username);

    @Insert("insert into user(username, password, create_time, update_time) values " +
            "(#{username},#{password},#{createTime},#{updateTime})")
    void insert(User user1);
}
