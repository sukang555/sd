package com.util;


import com.component.ApplicationUtils;
import com.component.BeanFacade;
import com.source.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author sukang on 2019/12/9 16:11
 */
public class EncryptUtils {

    private  static Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

    private static  String PRIVATE_KEY;

    private static  String PUBLIC_KEY;

    static {
        PropertiesManager propertiesManager = ApplicationUtils.getBean("propertiesManager", PropertiesManager.class);

        PUBLIC_KEY = propertiesManager.getPublicKey();

        PRIVATE_KEY = propertiesManager.getPrivateKey();
    }

    public static String decryptByPrivateKey(String text){
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(PRIVATE_KEY));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(text));
            return new String(result);
        } catch (Exception e) {
            logger.error("加密异常异常信息为:",e);
        }
        return "";
    }

    public static String encryptByPublicKey(String text){
        try {
            X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(
                    Base64.getDecoder().decode(PUBLIC_KEY));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            logger.error("解密异常异常信息为:",e);
        }
        return "";
    }

}
