package com.component;


import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author sukang on 2019/2/2.
 */
@ManagedBean("beanFacade")
public class BeanFacade {


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
