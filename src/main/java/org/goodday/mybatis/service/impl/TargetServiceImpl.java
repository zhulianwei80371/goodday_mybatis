package org.goodday.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.goodday.mybatis.entity.Target;
import org.goodday.mybatis.entity.TargetExample;
import org.goodday.mybatis.mapper.TargetMapper;
import org.goodday.mybatis.service.ITargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhulw
 * @since 2025-05-07
 */
@Service
public class TargetServiceImpl  extends ServiceImpl<TargetMapper, Target> implements ITargetService {
    @Override
    public Target selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public List<Target> selectByExample(String indexName) {
        return List.of();
    }

    @Override
    public int insertByMapper(Target target) {
        return 0;
    }

    @Override
    public boolean updateTarget(Target target) {
        return false;
    }
//    @Autowired
//    private TargetMapper  targetMapper;
//    @Autowired
//    private TargetCustomMapper targetCustomMapper;
//
//    @Override
//    public Target selectByPrimaryKey(String id){
//        return targetCustomMapper.selectByPrimaryKey(id);
//    }
//    @Override
//    public List<Target> selectByExample(String indexName){
//        TargetExample example = new TargetExample();
//        example.createCriteria().andIndexNameEqualTo("指标名1");
//        return targetCustomMapper.selectByExample(example);
//    }
//    @Override
//    public int insertByMapper(Target target){
//        return targetMapper.insert(target);
//    }
//
//    public boolean updateTarget(Target target) {
//        // MyBatis-Plus 通用更新
//        return updateById(target);
//    }
}
