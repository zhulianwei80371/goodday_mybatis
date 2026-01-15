package org.goodday.mybatis;

import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnhancedMySqlQuery extends MySqlQuery {

    @Override
    public String tableFieldsSql() {
        // 使用能够获取长度信息的查询
        return "SELECT " +
                "    COLUMN_NAME, " +
                "    DATA_TYPE, " +
                "    CHARACTER_MAXIMUM_LENGTH, " +
                "    NUMERIC_PRECISION, " +
                "    NUMERIC_SCALE, " +
                "    COLUMN_COMMENT, " +
                "    COLUMN_KEY, " +
                "    IS_NULLABLE, " +
                "    COLUMN_DEFAULT, " +
                "    EXTRA " +
                "FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_SCHEMA = DATABASE() " +
                "    AND TABLE_NAME = '%s' " +
                "ORDER BY ORDINAL_POSITION";
    }

    // 注意：3.5.3版本中，fieldCustom()方法很重要！
    @Override
    public String[] fieldCustom() {
        // 返回自定义字段，这些字段会放入field.customMap中
        return new String[]{
                "CHARACTER_MAXIMUM_LENGTH",  // 字符长度
                "NUMERIC_PRECISION",          // 数字精度
                "NUMERIC_SCALE",              // 小数位
                "IS_NULLABLE",                // 是否可空
                "COLUMN_DEFAULT",             // 默认值
                "EXTRA"                       // 额外信息（如auto_increment）
        };
    }

    @Override
    public String fieldType() {
        return "DATA_TYPE";
    }

    @Override
    public String fieldName() {
        return "COLUMN_NAME";
    }

    @Override
    public String fieldComment() {
        return "COLUMN_COMMENT";
    }

    @Override
    public String fieldKey() {
        return "COLUMN_KEY";
    }

    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return "auto_increment".equals(results.getString("EXTRA"));
    }
}