package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.constant.CommonConstant;
import com.component.ApplicationUtils;
import com.source.PropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author sukang on 2020/1/19 13:56
 */
@Slf4j
public class IpUtils {

    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (invalidIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (invalidIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (invalidIp(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (invalidIp(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (invalidIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean invalidIp(String ip) {
        return StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip);
    }



    public static String query(String ip){
        PropertiesManager propertiesManager = ApplicationUtils.getBean(PropertiesManager.class);
        Map<String, String> params = new HashMap<String, String>() {{
            put("ip",ip);
            put("token",propertiesManager.getIpToken());
        }};
        try {
            String uriParams = WebClientUtil.buildUriParams(propertiesManager.getIpUri(), params);

            String jsonStr = WebClientUtil.doGet(uriParams, null, params, String.class);
            JSONObject json = JSON.parseObject(jsonStr);
            if (Objects.equals(CommonConstant.OK,json.getString("ret"))){
                JSONArray jsonArray = json.getJSONArray("data");
                StringBuilder stringBuilder = new StringBuilder();
                jsonArray.forEach(t -> stringBuilder.append(String.valueOf(t)));
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            log.error("获取ip归属地信息异常",e);
        }
        return ip;
    }
}
