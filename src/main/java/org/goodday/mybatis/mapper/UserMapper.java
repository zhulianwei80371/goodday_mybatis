package org.goodday.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.goodday.mybatis.entity.User;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO t_user (id, name, country) VALUES (#{id}, #{name}, #{country})")
    void insert(@Param("id") Long id, @Param("name") String name, @Param("country") String country);

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User selectUserById(@Param("id") Long id);

    @Insert("INSERT INTO t_user (id, name, country) VALUES (#{id}, #{name}, #{country})")
    void insertUser(User user);
}