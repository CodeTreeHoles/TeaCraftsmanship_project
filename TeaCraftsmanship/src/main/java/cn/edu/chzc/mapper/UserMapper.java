package cn.edu.chzc.mapper;

import cn.edu.chzc.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("Select * from user where name=#{name}")
    User login(User user);

    @Insert("insert into user (name,personal_profile,password,phone,salt) values (#{name},#{personalProfile},#{password},#{phone},#{salt})")
    void enroll(User user);

//    @Insert("insert into ")
}
