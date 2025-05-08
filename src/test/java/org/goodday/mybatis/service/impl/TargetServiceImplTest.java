package org.goodday.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.goodday.mybatis.entity.Target;
import org.goodday.mybatis.mapper.TargetMapper;
import org.goodday.mybatis.service.ITargetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TargetServiceImplTest {
     @Autowired
     private TargetMapper targetMapper;
     @Autowired
     private ITargetService targetService;

     @Test
     public void test1(){
          Target target = new Target();
          target.setId("008");
          target.setIndexName("指标名1008");
          target.setValue(new BigDecimal("4000008"));
          target.setDataTime(new Date());
          targetService.updateTarget(target);
          System.out.println(targetService.selectByPrimaryKey("008"));
     }

     @Test
     public void test3(){
          // 循环打印
          targetService.selectByExample("指标名1").stream().forEach(e -> System.out.println(e));
     }

     @Test
     public void test2(){
          Target target = new Target();
          target.setId("011");
          target.setIndexName("指标名1");
          target.setValue(new BigDecimal("4000"));
          target.setDataTime(new Date());
          targetService.save(target);
//          targetService.insertByMapper(target);
     }

     @Autowired
     private SqlSessionFactory sqlSessionFactory;

     @Test
     public void debugMappedStatements() {
          Configuration config = sqlSessionFactory.getConfiguration();
          for (String statement : config.getMappedStatementNames()) {
               System.out.println(">> " + statement);
          }
     }
}