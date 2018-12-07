package com.datasource;

import java.lang.annotation.*;

/**
 * @author sukang
 */

/**
 * 创建拦截设置数据源的注解
 */
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicRouteDataSource {

    String value() default "";
}
