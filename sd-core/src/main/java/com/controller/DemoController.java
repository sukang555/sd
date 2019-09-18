package com.controller;

import com.common.dto.ResponseBean;
import com.common.exception.Exceptions;
import com.common.util.BeanUtil;
import com.controller.core.BaseController;
import com.dto.StatusInfo;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * @author sukang
 */
@RestController
@RequestMapping(value = "/public")
public class DemoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/uuid/{num}")
    public String uuId(@PathVariable("num") String num){
        int parseInt;
        if (NumberUtils.isCreatable(num) && (parseInt = Integer.parseInt(num)) > 0 ){
            StringBuilder stringBuilder = new StringBuilder(
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>Title</title>\n" +
                            "\n" +
                            "    <style type=\"text/css\">\n" +
                            "        body{font-family:\"Microsoft YaHei\"}\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "<body>");
            for (int i = 0; i < parseInt; i++) {
                String s = UUID.randomUUID().toString();
                stringBuilder.append(s.replace("-",""));
                stringBuilder.append("<br>");
            }
            stringBuilder.append("</body>\n" +
                    "</html>");
            return stringBuilder.toString();
        }else {
            String s = UUID.randomUUID().toString();
            return s.replace("-","");
        }
    }

    @PostMapping("mq")
    public ResponseBean sendMq(){
        String message = "{\"applyNo\":\"2021846\",\"code\":\"100000\"," +
                "\"link\":\"REQUEST_FUNDS\",\"message\":\"success\"," +
                "\"needSupply\":true} ";
        rabbitTemplate.convertAndSend("sukang",message);

        return ResponseBean.ok("success");
    }


    @PostMapping("/success")
    public ResponseBean success(Map<String,String> map){
        logger.info("test"+BeanUtil.toJsonStr(map));
        return ResponseBean.ok("/feature/test/1.1");
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
        logger.info(BeanUtil.toJsonStr(statusInfo));
        return ResponseBean.ok("");

    }
}
