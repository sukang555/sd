package com;

import com.common.dto.ResponseBean;
import org.springframework.aop.MethodBeforeAdvice;

import java.io.Serializable;

/**
 * @author sukang
 */
public class HelloService {



    public ResponseBean pushData(Serializable body){
        System.out.println("执行推送数据方法"+body);
        return ResponseBean.ok("success");
    }
}
