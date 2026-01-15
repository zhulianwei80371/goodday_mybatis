package org.goodday.mybatis;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Arrays;
import java.util.Collections;

public class  OracleGenerator{
    public static void main(String[] args) {
        // 只修改这里：MySQL URL 改为 Oracle URL
        String url = "jdbc:oracle:thin:@//192.168.43.73:1521/insound";  // Oracle URL
        // 或者：jdbc:oracle:thin:@192.168.43.73:1521:ORCL
        String username = "insound";
        String password = "123456";

        // 表名保持原样（如果Oracle中是小写表名）
        String[] tableNames = {"DATA_QUALITY_RULES", "DD_CHECK_RULE"};

        String projectPath = System.getProperty("user.dir");
        System.out.println("项目路径: " + projectPath);

        // 检查模板文件
        checkTemplates(projectPath);

        // 手动删除旧文件确保重新生成
        cleanOldFiles(projectPath, tableNames);

        // 生成TargetRepo
        generateTargetRepo(url, username, password, tableNames, projectPath);

        // 生成Repository
        generateRepository(url, username, password, tableNames, projectPath);

        // 生成Step
        generateStep(url, username, password, tableNames, projectPath);

        generateMapperOnly(url, username, password, tableNames, projectPath);

        System.out.println("生成完成！");
    }

    private static void generateTargetRepo(String url, String username, String password, String[] tableNames, String projectPath) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("zhulw")
                            .outputDir(projectPath + "/src/main/java")
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("com.dcits.ensemble.ddmp.business")
                            .entity("targetrepo");
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames)
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .enableLombok()
                            .formatFileName("%sTargetRepo");
                })
                .templateConfig(builder -> {
                    builder.disable()
                            .entity("templates/targetrepo.java");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    private static void generateRepository(String url, String username, String password, String[] tableNames, String projectPath) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("zhulw")
                            .outputDir(projectPath + "/src/main/java")
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("com.dcits.ensemble.ddmp.business")
                            .entity("repository");
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames)
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .enableLombok()
                            .formatFileName("%sRepository");
                })
                .templateConfig(builder -> {
                    builder.disable()
                            .entity("templates/repository.java");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    private static void generateStep(String url, String username, String password, String[] tableNames, String projectPath) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("zhulw")
                            .outputDir(projectPath + "/src/main/java")
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("com.dcits.ensemble.ddmp.business")
                            .entity("step");
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames)
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .enableLombok()
                            .formatFileName("%sStep");
                })
                .templateConfig(builder -> {
                    builder.disable()
                            .entity("templates/step.java");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    private static void checkTemplates(String projectPath) {
        String templateDir = projectPath + "/src/main/resources/templates/";
        java.io.File dir = new java.io.File(templateDir);

        if (dir.exists()) {
            String[] files = dir.list();
            System.out.println("找到模板文件: " + Arrays.toString(files));

            String[] requiredTemplates = {"targetrepo.java.ftl", "repository.java.ftl", "step.java.ftl","mapper.xml.ftl"};
            for (String required : requiredTemplates) {
                java.io.File file = new java.io.File(dir, required);
                if (file.exists()) {
                    System.out.println("✓ 找到模板: " + required);
                } else {
                    System.out.println("✗ 缺失模板: " + required);
                }
            }
        } else {
            System.out.println("✗ 模板目录不存在: " + templateDir);
            dir.mkdirs();
        }
    }
    private static void generateMapperOnly(String url, String username, String password,
                                           String[] tableNames, String projectPath) {

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("zhulw")
                            .outputDir(projectPath + "/src/main/java")
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("com.dcits.ensemble.ddmp.business")
                            .entity("entity")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(
                                    OutputFile.xml,
                                    projectPath + "/src/main/resources/mapper"
                            ));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames)
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .enableLombok()
                            .formatFileName("%s")
                            .enableTableFieldAnnotation()
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .naming(NamingStrategy.underline_to_camel)
                            .mapperBuilder()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    private static void cleanOldFiles(String projectPath, String[] tableNames) {
        for (String tableName : tableNames) {
            String entityName = convertToCamelCase(tableName.replace("t_", ""));
            System.out.println("实体类名: " + entityName);

            String[] filesToDelete = {
                    projectPath + "/src/main/java/com/dcits/ensemble/ddmp/business/targetrepo/" + entityName + "TargetRepo.java",
                    projectPath + "/src/main/java/com/dcits/ensemble/ddmp/business/repository/" + entityName + "Repository.java",
                    projectPath + "/src/main/java/com/dcits/ensemble/ddmp/business/step/" + entityName + "Step.java"
            };

            for (String filePath : filesToDelete) {
                java.io.File file = new java.io.File(filePath);
                if (file.exists() && file.delete()) {
                    System.out.println("✓ 已删除旧文件: " + filePath);
                }
            }
        }
    }

    private static String convertToCamelCase(String str) {
        String[] parts = str.split("_");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}