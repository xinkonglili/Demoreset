package com.nk.demoreset.mapper;

import com.nk.demoreset.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Insert("insert into user(name,username,password,department_id,email,images,status) " +
            "values (#{name},#{username},#{password},#{department_id},#{email},#{username},#{images},#{status})")
    Boolean addUser(User user);



}
