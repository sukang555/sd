package com.controller.core;

import com.common.dto.ResponseBean;
import com.common.dto.StatusInfo;
import com.common.exception.Exceptions;
import com.common.util.WebClientUtil;
import net.sf.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sukang
 */
public class BaseController {



    public void checkParams(BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            List<String> collect = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            throw Exceptions.newPramException(collect.toString());
        }
    }




    public static void main(String[] args) {

        StatusInfo statusInfo = new StatusInfo();
        statusInfo.setApplyNo("");
        statusInfo.setType("");

        Map<String, String> header = new HashMap<>(3);
        header.put("Autho","sukang");

        ResponseBean responseBean = WebClientUtil.doPost("http://127.0.0.1:18081/public/status-info",
                statusInfo, ResponseBean.class,header);

        System.out.println(JSONObject.fromObject(responseBean).toString());
    }


}
