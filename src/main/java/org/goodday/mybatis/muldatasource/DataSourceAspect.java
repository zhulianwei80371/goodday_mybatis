package org.goodday.mybatis.muldatasource;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Order(1)
@Component
public class DataSourceAspect {


    @Pointcut("@annotation(org.goodday.mybatis.muldatasource.MutiDataSourceAnnotaton)"
            + "||  @within(org.goodday.mybatis.muldatasource.MutiDataSourceAnnotaton)")
    public void dsPointCut() {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MutiDataSourceAnnotaton mutiDataSourceAnnotaton = getDataSource(joinPoint);
        if (mutiDataSourceAnnotaton != null) {
            DynamicDataSourceContextHolder.setDataSourceType(mutiDataSourceAnnotaton.value().name());
        }
        try {
            return joinPoint.proceed();
        } finally {
            //  销毁数据源  在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public MutiDataSourceAnnotaton getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        MutiDataSourceAnnotaton mutiDataSourceAnnotaton = AnnotationUtils.findAnnotation(signature.getMethod(), MutiDataSourceAnnotaton.class);
        if (Objects.nonNull(mutiDataSourceAnnotaton)) {
            return mutiDataSourceAnnotaton;
        }
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), MutiDataSourceAnnotaton.class);
    }
}