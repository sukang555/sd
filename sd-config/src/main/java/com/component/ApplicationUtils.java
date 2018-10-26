package com.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.ManagedBean;
import java.util.Map;

/**
 * Created by sukang on 2018/7/10.
 */
@ManagedBean
public class ApplicationUtils implements  ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> Map<String,T> getBeans(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }


    public static  <T> T getBean(String name,Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static <E> E getBean(Class<E> clazz){
        return applicationContext.getBean(clazz);
    }



}
