package org.goodday.mybatis.service;

import org.goodday.mybatis.entity.Target;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhulw
 * @since 2025-05-07
 */
public interface ITargetService  extends IService<Target> {
    public Target selectByPrimaryKey(String id);
    public List<Target> selectByExample(String indexName);
    public int insertByMapper(Target target);
    public boolean updateTarget(Target target);

}
