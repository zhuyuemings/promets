package ltd.jezhu.promets.wx.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

/**
 * AES工具
 * @author ymzhu
 * @date 2019/3/26 17:01
 */
class WxAesUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES解密
     * @param content content
     * @param keyByte keyByte
     * @param ivByte  ivByte
     * @return {@link byte[]}
     * @author ymzhu
     * @date 2019/3/26 17:00
     */
    static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");
            // 生成iv
            AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
            params.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
            // 初始化
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
