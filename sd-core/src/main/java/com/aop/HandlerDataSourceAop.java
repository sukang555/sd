package com.aop;

import com.datasource.DataSourceNames;
import com.datasource.DynamicRouteDataSource;
import com.datasource.HandlerDataSource;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import javax.annotation.ManagedBean;
import java.lang.reflect.Method;

/**
 * @author sukang
 */

@Aspect
@ManagedBean
public class HandlerDataSourceAop implements Ordered{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *   //@within 在类上设置
        // @annotation 在方法上进行设置
     */
    @Pointcut("@annotation(com.datasource.DynamicRouteDataSource) ||" +
            " @within(com.datasource.DynamicRouteDataSource)")
    private void pointcut(){}

    @Around("pointcut()")
    public Object beforeDataSource(ProceedingJoinPoint joinPoint) throws Throwable{

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        DynamicRouteDataSource dataSource = method.getAnnotation(DynamicRouteDataSource.class);
        if ( dataSource != null ){
            HandlerDataSource.setDataSource(StringUtils.isBlank(dataSource.value())
                ? DataSourceNames.FIRST: dataSource.value());
        }else {
            DynamicRouteDataSource annotation = method.getDeclaringClass()
                    .getAnnotation(DynamicRouteDataSource.class);
            HandlerDataSource.setDataSource(
                    (annotation == null || StringUtils.isBlank(annotation.value()))
             ? DataSourceNames.FIRST:annotation.value()
            );
        }


        try {
            return joinPoint.proceed();
        } finally {
            HandlerDataSource.clearDataSource();
            logger.debug("clean datasource");
        }
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
