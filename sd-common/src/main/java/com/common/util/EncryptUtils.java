package com.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDPnUpCF4pvvAAipCahtF6V5YsZFpj6s9X4y7iHg4y/E/6wFJwvZRzw6UpzE3oVTUjLmKF/qTZnw4u9jtKT+tpQekayXn1fLSXV+rbdWJaWg7eYUpo2Tn+9KEIL7TmJvM56lC9Wva5N1zjEDNdB8ag/7R3NXAmQXgL1BtBocRN9ky7UCQvjoGULGJdhFosembOEm/LLmRexI5UDauoeOsQd3kBuCdMzsXOzqOENRG/IXdQ8GAQS1FKNWvXAqaqYlMIS2tZGv27kmPWiFgVAgEVqHbSC6cNcqQqMUJs2jIi+EudJb5ySsrNs3khxhpSWIuUVyWKjSzTq8X2rsA5s1kZ1AgMBAAECggEBAMHFTRx4ejWk4v9x87hy7uC6d+O3ippapHdsNFMOfZuNXuUap1c46RUUt+7Fm+kr06hqUTkkRmn8QX1vQqzosT/xj/BEboEZUznuoSPf3dSnJQDe/512EJUMcPBOvagHdzNDARfVO0zdddbNIU8j6+OWPH2BzE9Y3yuv622/M/C7cpieJR7zlfywPNCxX6VIq5cSYbYU7K8qQoiHn/Cw+sNZ1k9UWgneoS6Ttd2T9/Ikr1zR982ZOMHPW45SYIiDNIaGW1Q2RSgzPHy2hVX4D2tGGh9zsad4TYNtNao3eli6BGzF6B9atiKK4ZPZUacMgAwBHu5oH52mmJihTqcr8hUCgYEA7uL5ngzqeKQoyyYbGA/kQpmujW27yYEZEqJSMumQC82KGbxnSPcsY5TqmChjunBsynJJ3EydMC0q85NaUu21oyK9VUcVx59SMjYLtVh/zyhyv6OT3WTxP99TNXTc3N5P1n6GwLBk80eC9U+G9PMuI2OMB4IW6q3H9N4Sg/Xlh7sCgYEA3nzNmJDPQdFnmUzbUEZxTSSkaPoCgltJoOYrpulC5f+tGhqaTUXe/MEG/2ZNtQmbhum6pkE3SaM2y6PiBiOVgqHOJuRK8p2uGl7/vFtgE6vWXl0kIYK9cR7gTiqqIcWnHJEPO+ns/281tyhUzdx6So5yVEFa78aAMpX38hCKj48CgYEA4VO08hDqxXrKg7xgyBSNUQjW7c442DvuY3Y6pI1McuWgQuBEaD3FX/TiRz2pu/05kMe2xZZ6miuwnOo7w4c20jb9ccfIUnAEPxpdduOQQt27RupLmgQRzxaezQh7H9aOZhsYjf6Uaijk7PDjsn8C4RyPw7FAXoIm3uDthA1YQOsCgYAAmBa4dJKJG3UKXMEtKrL8wL9LpHdDP9kne9XcAT1zfcZJjo6g7DHXPPC21+AXPqpSpucfsIbkRHJOAucEmy24BpSVWAC3OyKO4XgPWbMc+lV9NQR2rnGxDqMMsmJ36p4ynlSLFWnTmMls7sejMRaGgnDdtrr2bvJCsFHULKUiEwKBgDVF0Lg5OKxnT2aNyNKjj0HURAWVMh8xUcPgtTrzAnugZx5XdkEZ6VLA/TiY3YgA2G0tYT2BiJfBe68XtefAIZ4lzPUuazKdFEOxlYYLGcC5jCZEYQr0+6KZ2a7f02pFp8XBme3kn2HFbDY7NNxKRKpTRgOJEb1AmphprC3N0jKU";

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz51KQheKb7wAIqQmobReleWLGRaY+rPV+Mu4h4OMvxP+sBScL2Uc8OlKcxN6FU1Iy5ihf6k2Z8OLvY7Sk/raUHpGsl59Xy0l1fq23ViWloO3mFKaNk5/vShCC+05ibzOepQvVr2uTdc4xAzXQfGoP+0dzVwJkF4C9QbQaHETfZMu1AkL46BlCxiXYRaLHpmzhJvyy5kXsSOVA2rqHjrEHd5AbgnTM7Fzs6jhDURvyF3UPBgEEtRSjVr1wKmqmJTCEtrWRr9u5Jj1ohYFQIBFah20gunDXKkKjFCbNoyIvhLnSW+ckrKzbN5IcYaUliLlFclio0s06vF9q7AObNZGdQIDAQAB";


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
