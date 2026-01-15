<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package.Entity}">
<sql id="PrimaryKey_Where">
    <where>
        <#list table.fields as field>
            <#if field.keyFlag>
        AND ${field.columnName} = <#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse>
            </#if>
        </#list>
    </where>
</sql>
<sql id="Table_Name">
    ${table.name}
</sql>
<sql id="Base_Column">
    <trim suffixOverrides=",">
        <#list table.fields as field>
        ${field.columnName},
        </#list>
    </trim>
</sql>
<resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
    <#list table.fields as field>
        <#if field.keyFlag>
    <id column="${field.columnName}" property="${field.propertyName}" />
        <#else>
    <result column="${field.columnName}" property="${field.propertyName}" />
        </#if>
    </#list>
</resultMap>
<!-- 修正 Base_Where：简化版本 -->
<sql id="Base_Where">
    <where>
        <#list table.fields as field>
            <#assign javaType = field.propertyType>
            <#assign columnName = field.columnName>
            <#assign fieldName = field.propertyName>
            <#if javaType == "String">
        <if test="${fieldName} != null and ${fieldName} != ''">
            AND ${columnName} = <#noparse>#{</#noparse>${fieldName}<#noparse>, jdbcType=VARCHAR}</#noparse>
        </if>
            <#else>
        <if test="${fieldName} != null">
            AND ${columnName} = <#noparse>#{</#noparse>${fieldName}<#noparse>}</#noparse>
        </if>
            </#if>
        </#list>
    </where>
</sql>
<!-- 修正 Base_Set：简化版本 -->
<sql id="Base_Set">
    <set>
        <#list table.fields as field>
            <#if !field.keyFlag>
                <#assign javaType = field.propertyType>
                <#assign columnName = field.columnName>
                <#assign fieldName = field.propertyName>

                <#if javaType == "String">
        <if test="${fieldName} != null and ${fieldName} != ''">
            ${columnName} = <#noparse>#{</#noparse>${fieldName}<#noparse>, jdbcType=VARCHAR}</#noparse>,
        </if>
                <#else>
        <if test="${fieldName} != null">
            ${columnName} = <#noparse>#{</#noparse>${fieldName}<#noparse>}</#noparse>,
        </if>
                </#if>
            </#if>
        </#list>
    </set>
</sql>
<sql id="Base_Select">
    SELECT
    <include refid="Base_Column"/>
    FROM
    <include refid="Table_Name"/>
    <include refid="Base_Where"/>
</sql>
<update id="update">
    UPDATE
    <include refid="Table_Name"/>
    <include refid="Base_Set"/>
    <include refid="PrimaryKey_Where"/>
</update>
<select id="count" parameterType="java.util.Map" resultType="int">
    SELECT count(1) FROM
    <include refid="Table_Name"/>
    <include refid="Base_Where"/>
</select>
<select id="selectOne" resultMap="BaseResultMap">
    <include refid="Base_Select"/>
</select>
<select id="selectList" resultMap="BaseResultMap">
    <include refid="Base_Select"/>
    ORDER BY CREATE_TIME DESC
</select>
<!-- 简化 insert -->
<insert id="insert">
    INSERT INTO
    <include refid="Table_Name"/>
    <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list table.fields as field>
            <#assign fieldName = field.propertyName>
        <if test="${fieldName} != null">
            ${field.columnName},
        </if>
        </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list table.fields as field>
            <#assign fieldName = field.propertyName>
        <if test="${fieldName} != null">
            <#noparse>#{</#noparse>${fieldName}<#noparse>}</#noparse>,
        </if>
        </#list>
    </trim>
</insert>
<delete id="deleteById">
    DELETE FROM
    <include refid="Table_Name"/>
    <include refid="PrimaryKey_Where"/>
</delete>
<!-- 优化 insertBatch：增加数据类型处理 -->
<insert id="insertBatch" parameterType="java.util.List">
    INSERT INTO
    <include refid="Table_Name"/>
    <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list table.fields as field>
        ${field.columnName},
        </#list>
    </trim>
    VALUES
    <#noparse><foreach collection="list" separator="," item="item"></#noparse>
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list table.fields as field>
                <#assign javaType = field.propertyType>
                <#assign fieldName = field.propertyName>
                <#if javaType == "String">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=VARCHAR}</#noparse>,
                <#elseif javaType == "Date" || javaType == "LocalDateTime" || javaType == "LocalDate" || javaType == "Timestamp">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=TIMESTAMP}</#noparse>,
                <#elseif javaType == "BigDecimal">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=DECIMAL}</#noparse>,
                <#elseif javaType == "Boolean">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=BOOLEAN}</#noparse>,
                <#elseif javaType == "Integer">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=INTEGER}</#noparse>,
                <#elseif javaType == "Long">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=BIGINT}</#noparse>,
                <#elseif javaType == "Double">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=DOUBLE}</#noparse>,
                <#elseif javaType == "Float">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=FLOAT}</#noparse>,
                <#elseif javaType == "Byte[]" || javaType == "byte[]">
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>, jdbcType=BLOB}</#noparse>,
                <#else>
            <#noparse>#{item.</#noparse>${fieldName}<#noparse>}</#noparse>,
                </#if>
            </#list>
        </trim>
    <#noparse></foreach></#noparse>
</insert>
</mapper>