package org.goodday.mybatis.muldatasource;

/**
 ** 动态数据源
 **   @author zlw
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource(DataSource defaultDataSourceAnnotaton, Map<Object, Object> targetDataSources) {
        //设置默认数据源
        super.setDefaultTargetDataSource(defaultDataSourceAnnotaton);
        //设置目标数据源的映射
        super.setTargetDataSources(targetDataSources);
        //初始化
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String ds = DynamicDataSourceContextHolder.getDataSourceType();
        System.out.println("【DynamicDataSource】当前选择的数据源是：" + ds);
        return ds;
    }
}
