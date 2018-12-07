package com.datasource;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author sukang
 */

@Aspect
public class HandlerDataSourceAop {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *   //@within 在类上设置
        // @annotation 在方法上进行设置
     */
    @Pointcut("@within(com.datasource.DynamicRouteDataSource)" +
            " || @annotation(com.datasource.DynamicRouteDataSource)")
    private void pointcut(){}

    @Around("pointcut")
    public Object beforeDataSource(ProceedingJoinPoint joinPoint) throws Throwable{

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        DynamicRouteDataSource dataSource = method.getAnnotation(DynamicRouteDataSource.class);
        if ( dataSource != null){
            HandlerDataSource.setDataSource(dataSource.value());
        }else {
            DynamicRouteDataSource annotation = signature.getClass()
                    .getAnnotation(DynamicRouteDataSource.class);
            HandlerDataSource.setDataSource(StringUtils.isBlank(annotation.value())
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

}
