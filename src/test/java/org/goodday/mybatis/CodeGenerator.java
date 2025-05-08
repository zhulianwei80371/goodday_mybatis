package org.goodday.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.sql.Types;
import java.util.Collections;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * 完整可运行的 MyBatis-Plus 代码生成器示例
 * 使用 FastAutoGenerator + Freemarker + 自定义类型转换
 * 支持自定义数据库连接配置、生成路径、作者、模块等
 */
public class CodeGenerator {
    public static void main(String[] args) {
        // JDBC 数据源配置
        String url = "jdbc:mysql://192.168.199.145:3306/insound1?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String username = "insound";
        String password = "123456";

        // 输出路径配置
        String projectPath = System.getProperty("user.dir");
        Map<OutputFile, String> pathMap = new HashMap<>();
        pathMap.put(OutputFile.xml, projectPath + "/src/main/resources/org/goodday1/mybatis/mapper");
        pathMap.put(OutputFile.mapper, projectPath + "/src/main/java/org/goodday1/mybatis/mapper");
        pathMap.put(OutputFile.service, projectPath + "/src/main/java/org/goodday1/mybatis/service");
        pathMap.put(OutputFile.serviceImpl, projectPath + "/src/main/java/org/goodday1/mybatis/service/impl");
        pathMap.put(OutputFile.controller, projectPath + "/src/main/java/org/goodday1/mybatis/controller");
        pathMap.put(OutputFile.entity, projectPath + "/src/main/java/org/goodday1/mybatis/entity");

        // 启动代码生成器
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username, password))
                // 可选：自定义类型转换器（如 SMALLINT → INTEGER）
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            if (metaInfo.getJdbcType().TYPE_CODE == Types.SMALLINT) {
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("zhulw")
//                            .enableSwagger()
                            .enableSpringdoc()
                            .outputDir(projectPath + "/src/main/java");
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("org.goodday") // 父包名
                            .moduleName("mybatis") // 子模块名
                            .pathInfo(pathMap); // 自定义路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("t_target") // 需要生成的表名
                            .addTablePrefix("t_", "c_");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute();
    }
}
