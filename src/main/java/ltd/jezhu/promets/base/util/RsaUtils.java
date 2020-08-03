package ltd.jezhu.promets.base.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/**
 * RSA加密解密工具
 *
 * @author ymzhu
 * @date 2019/7/3 9:27
 */
public class RsaUtils {

    public static RSAPublicKey getPublicKey(String modulus, String exponent) {
        //exponent我这边传入的是5位的数字，如10101，这个客户提供的，真实值不能提供
        try {
            BigInteger b1 = new BigInteger(modulus, 16);
            //此处为进制数
            BigInteger b2 = new BigInteger(exponent, 16);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String... args) {
        String m = "";

    }

}
