package com.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.common.exception.JsonTransException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.org.apache.xml.internal.utils.ObjectVector;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author sukang
 */
public class BeanUtil {

    private static final  ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    static {
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //key可以不带双引号
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //key value 可以是单引号
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true) ;
        //允许出现特殊字符和转义符
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static <T> T fromStrToObj(String jsonStr,Class<T> clazz){
        try {
            return OBJECT_MAPPER.readValue(jsonStr,clazz);
        }catch (Exception e){
            logger.error("json转换异常",e);
            throw  new JsonTransException(e);
        }
    }

    public static byte[] fromObjToByte(Object object){
        try {

            return OBJECT_MAPPER.writeValueAsBytes(object);

        }catch (Exception e){
            logger.error("序列化异常",e);
            throw  new JsonTransException(e);
        }
    }



    public static String toJsonStr (Object object){
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        }catch (Exception e){
            logger.error("json转换异常",e);
        }
        return "";
    }




    public Map<String,String> beanToMap(Object bean){
        /*ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern(DateUtil.DEFAULT_DATE_TIME_FORMAT);
        convertUtils.register(dateConverter, String.class);
        return new BeanUtilsBean(convertUtils).describe(object);*/

        return null;
    }





}
