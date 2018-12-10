package com.controller;

import com.common.dto.ResponseBean;
import com.common.dto.StatusInfo;
import com.common.exception.Exceptions;
import com.controller.core.BaseController;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author sukang
 */
@RestController
@RequestMapping(value = "/public")
public class DemoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RabbitTemplate rabbitTemplate;




    @PostMapping("mq")
    public ResponseBean sendMq(){
        String message = "{\"applyNo\":\"2021846\",\"code\":\"100000\"," +
                "\"link\":\"REQUEST_FUNDS\",\"message\":\"success\"," +
                "\"needSupply\":true} ";
        rabbitTemplate.convertAndSend("sukang",message);
        return ResponseBean.ok("success");
    }


    @PostMapping("/success")
    public Mono<ResponseBean> success(Map<String,String> map){
        return Mono.just(ResponseBean.ok(""));

    }


    @PostMapping(value = "/status-info")
    public ResponseBean getStatusInfo(
            @RequestBody @Validated StatusInfo statusInfo,
            BindingResult bindingResult, HttpServletRequest request){

        System.out.println(request.getHeader("Autho"));

        checkParams(bindingResult);

        if ("123".equals(statusInfo.getApplyNo())){
            throw Exceptions.newBusinessException("申请编号不存在");
        }
        logger.info(JSONObject.fromObject(statusInfo).toString());
        return ResponseBean.ok("");

    }
}
