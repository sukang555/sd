package com.component;

import com.config.RabbitMqConfigTest;
import com.config.RabbitMqConfigTest2;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author sukang on 2019/2/2.
 */
@ManagedBean("beanFacade")
public class BeanFacade {



    private RabbitMqConfigTest rabbitMqConfigTest;

    public static RabbitMqConfigTest getRabbitMqConfigTest() {
        return getBeanFacade().rabbitMqConfigTest;
    }

    @Inject
    @Named(value = "rabbitMqConfigTest")
    public void setRabbitMqConfigTest(RabbitMqConfigTest rabbitMqConfigTest) {
        this.rabbitMqConfigTest = rabbitMqConfigTest;
    }




    private RabbitMqConfigTest2 rabbitMqConfigTest2;

    public static RabbitMqConfigTest2 getRabbitMqConfigTest2() {
        return getBeanFacade().rabbitMqConfigTest2;
    }

    @Inject
    @Named(value = "rabbitMqConfigTest2")
    public void setRabbitMqConfigTest2(RabbitMqConfigTest2 rabbitMqConfigTest2) {
        this.rabbitMqConfigTest2 = rabbitMqConfigTest2;
    }





    private JobTask jobTask;

    public static JobTask getJobTask() {
        return getBeanFacade().jobTask;
    }

    @Inject
    @Named(value = "jobTask")
    public void setJobTask(JobTask jobTask) {
        this.jobTask = jobTask;
    }



    public static BeanFacade getBeanFacade(){
        return ApplicationUtils.getBeanFacade();
    }





}
