package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import com.dcits.comet.dao.annotation.TableType;
import com.dcits.comet.dao.annotation.TableTypeEnum;
import com.dcits.comet.dao.annotation.TablePk;
import com.dcits.comet.dao.annotation.TableColumn;
import com.dcits.comet.dao.model.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
<#if hasBigDecimal!false>
import java.math.BigDecimal;
</#if>

<#if table.comment?? && table.comment != "">
/**
 * ${table.comment}
 * @author ${author}
 * @since ${date}
 */
</#if>
@TableType(name = "${table.name}", value = TableTypeEnum.LEVEL)
@Data
<#if entityLombokModel>
@EqualsAndHashCode(callSuper = true)
</#if>
public class ${entity} extends BasePo {

<#list table.fields as field>
    <#assign javaType = field.propertyType>
    <#assign fieldName = field.propertyName>
    <#assign comment = field.comment!>
    <#if javaType?contains("Local")>
        <#assign javaType = "Date">
    </#if>
    <#if comment?length gt 0>
    /**${comment}*/
    </#if>
    <#if field.keyFlag>
    @TablePk(index = 1)
    </#if>
    <#-- 解析 metaInfo 字符串 -->
    <#assign metaInfo = field.metaInfo?string!''>
    <#assign length = "">
    <#assign scale = "">
    <#assign jdbcType = "">
    <#if metaInfo?contains("length=")>
        <#assign lengthStart = metaInfo?index_of("length=") + 7>
        <#assign lengthEnd = metaInfo?index_of(",", lengthStart)>
        <#if lengthEnd == -1>
            <#assign lengthEnd = metaInfo?index_of("}", lengthStart)>
        </#if>
        <#if lengthEnd != -1>
            <#assign length = metaInfo?substring(lengthStart, lengthEnd)?trim>
        </#if>
    </#if>
    <#if metaInfo?contains("scale=")>
        <#assign scaleStart = metaInfo?index_of("scale=") + 6>
        <#assign scaleEnd = metaInfo?index_of(",", scaleStart)>
        <#if scaleEnd == -1>
            <#assign scaleEnd = metaInfo?index_of("}", scaleStart)>
        </#if>
        <#if scaleEnd != -1>
            <#assign scale = metaInfo?substring(scaleStart, scaleEnd)?trim>
        </#if>
    </#if>
    <#if metaInfo?contains("jdbcType=")>
        <#assign typeStart = metaInfo?index_of("jdbcType=") + 9>
        <#assign typeEnd = metaInfo?index_of(",", typeStart)>
        <#if typeEnd == -1>
            <#assign typeEnd = metaInfo?index_of("}", typeStart)>
        </#if>
        <#if typeEnd != -1>
            <#assign jdbcType = metaInfo?substring(typeStart, typeEnd)?trim>
        </#if>
    </#if>
    <#-- 如果没有获取到 jdbcType，使用 columnType -->
    <#if !jdbcType?has_content && field.columnType?? && field.columnType.typeName??>
        <#assign jdbcType = field.columnType.typeName?upper_case>
    </#if>
    <#-- 生成 @TableColumn 注解 -->
    <#if jdbcType?has_content>
        <#if length?has_content>
            <#if scale?has_content && scale != "0">
    @TableColumn(desc = "${comment}", type = "${jdbcType}", length = "${length}", scale = "${scale}")
            <#else>
    @TableColumn(desc = "${comment}", type = "${jdbcType}", length = "${length}", scale = "0")
            </#if>
        <#else>
    @TableColumn(desc = "${comment}", type = "${jdbcType}",length = "0", scale = "0")
        </#if>
    <#else>
    @TableColumn(desc = "${comment}",length = "0", scale = "0")
    </#if>

    private ${javaType} ${fieldName};
</#list>
}