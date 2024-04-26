package com.bigevent.mapper;

import com.bigevent.pojo.User;
import com.bigevent.pojo.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface UserMapper {
    //查询用户
    User getByUser(Map map);

    @Insert("insert into user(username, password, create_time, update_time) values " +
            "(#{username},#{password},#{createTime},#{updateTime})")
    void insert(User user);
    /**
     * 更新数据
     *
     * @param user
     */
    @Update("update user set nickname=#{nickname},email=#{email} ,update_time=now() where id=#{id}")
    void update(UserDTO user);

    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvarter(String avatarUrl, Integer id);
    @Update("update user set password=#{password},update_time=now() where id=#{id}")
    void updatePwd(String password, Integer id);
}
