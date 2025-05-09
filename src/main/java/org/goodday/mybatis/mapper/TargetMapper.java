package org.goodday.mybatis.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.goodday.mybatis.entity.Target;
import org.goodday.mybatis.entity.TargetExample;
import org.goodday.mybatis.entity.User;

public interface TargetMapper extends BaseMapper<Target>{
    long countByExample(TargetExample example);

    int deleteByExample(TargetExample example);

    int deleteByPrimaryKey(String id);

    int insert(Target row);

    int insertSelective(Target row);

    List<Target> selectByExample(TargetExample example);

    Target selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") Target row, @Param("example") TargetExample example);

    int updateByExample(@Param("row") Target row, @Param("example") TargetExample example);

    int updateByPrimaryKeySelective(Target row);

    int updateByPrimaryKey(Target row);
}