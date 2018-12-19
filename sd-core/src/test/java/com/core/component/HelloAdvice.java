package com.core.component;

import com.common.util.BeanUtil;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * @author sukang
 */
public class HelloAdvice implements MethodBeforeAdvice,AfterReturningAdvice{

    @Override
    public void before(Method method, Object[] args,
                       @Nullable Object target) throws Throwable {

        System.out.println(method.getName());
        System.out.println("目标方法执行前数据为："+BeanUtil.toJsonStr(args));

    }

    @Override
    public void afterReturning(@Nullable Object returnValue,
                               Method method, Object[] args,
                               @Nullable Object target) throws Throwable {
        System.out.println("目标方法执行后"+ BeanUtil.toJsonStr(returnValue));
    }
}
