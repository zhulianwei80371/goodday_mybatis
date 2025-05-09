package org.goodday.mybatis.muldatasource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@MapperScan(basePackages = "org.goodday.mybatis.mapper")
public class MyBatisConfig {

//    @Bean
//    @Primary
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        return factoryBean.getObject();
//    }

//    @Bean
//    @Primary
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        return sqlSessionFactory.getObject();
//    }

    // mybatis plus不要手动


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void printResultMaps() {
        // 看看加载了多少个 resultMap
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        System.out.println("=== Loaded ResultMaps ===");
        configuration.getResultMapNames().forEach(System.out::println);
        // 看看加载了多少个 mapper
        System.out.println("加载的 Mapper 映射语句数量: " + configuration.getMappedStatementNames().size());
        configuration.getMappedStatementNames().forEach(System.out::println);
    }
}
