package org.goodday.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;

public class CodeGenerator3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.43.73:3306/insound?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String username = "insound";
        String password = "123456";

        // 只测试一个表
        String tableName = "t_abroad_guar_flow";

        String projectPath = System.getProperty("user.dir");
        System.out.println("项目路径: " + projectPath);

        // 检查模板文件
        checkTemplates(projectPath);

        // 手动删除旧文件确保重新生成
        cleanOldFiles(projectPath, tableName);

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("zhulw")
                            .outputDir(projectPath + "/src/main/java")
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("org.goodday1.mybatis")
                            .entity("repository");
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName)
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .enableLombok()
                            .formatFileName("%s");
                })
                .templateConfig(builder -> {
                    builder.disable()
                            .entity("templates/repository.java");
                })
                .injectionConfig(builder -> {
                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                        System.out.println("生成表: " + tableInfo.getEntityName());

                        // 添加Step类生成
                        builder.customFile(stepBuilder -> {
                            // 直接使用实体类名 + "Step"
                            stepBuilder.fileName("Step.java")  // 直接命名为Step.java
                                    .templatePath("templates/step.java.ftl")
                                    .packageName("com.dcits.ensemble.ddmp.batch.step.nbl")
                                    .enableFileOverride();
                        });
                    });
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

        System.out.println("生成完成！");
    }

    private static void checkTemplates(String projectPath) {
        String templateDir = projectPath + "/src/main/resources/templates/";
        java.io.File dir = new java.io.File(templateDir);

        if (dir.exists()) {
            String[] files = dir.list();
            System.out.println("找到模板文件: " + Arrays.toString(files));

            // 检查关键模板文件
            String[] requiredTemplates = {"repository.java.ftl", "step.java.ftl"};
            for (String required : requiredTemplates) {
                java.io.File file = new java.io.File(dir, required);
                if (file.exists()) {
                    System.out.println("✓ 找到模板: " + required);
                    try {
                        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
                        String firstLine = reader.readLine();
                        System.out.println("  模板首行: " + (firstLine != null ? firstLine : "空文件"));
                        reader.close();
                    } catch (Exception e) {
                        System.out.println("  无法读取模板内容");
                    }
                } else {
                    System.out.println("✗ 缺失模板: " + required);
                }
            }
        } else {
            System.out.println("✗ 模板目录不存在: " + templateDir);
            System.out.println("正在创建模板目录...");
            dir.mkdirs();
        }
    }

    private static void cleanOldFiles(String projectPath, String tableName) {
        // 根据表名生成实体类名
        String entityName = convertToCamelCase(tableName.replace("t_", ""));
        System.out.println("实体类名: " + entityName);

        String[] filesToDelete = {
                projectPath + "/src/main/java/org/goodday1/mybatis/repository/" + entityName + ".java",
                projectPath + "/src/main/java/com/dcits/ensemble/ddmp/batch/step/nbl/Step.java"
        };

        for (String filePath : filesToDelete) {
            java.io.File file = new java.io.File(filePath);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("✓ 已删除旧文件: " + filePath);
                } else {
                    System.out.println("✗ 删除失败: " + filePath);
                }
            } else {
                System.out.println("ℹ 文件不存在: " + filePath);
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