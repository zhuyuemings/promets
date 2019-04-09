package ltd.jezhu.promets.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ltd.jezhu.promets.exception.WxAesException;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 微信用户信息解密工具
 * @author ymzhu
 * @date 2019/4/9 9:12
 */
public class WxBizDataCrypt {

    /**
     * 非法密钥
     */
    private final static String ILLEGAL_AES_KEY = "-41001";
    /**
     * 非法初始向量
     */
    private final static String ILLEGAL_IV = "-41002";
    /**
     * 非法密文
     */
    private final static String ILLEGAL_BUFFER = "-41003";
    /**
     * 解码错误
     */
    private final static String DECODE_BASE_64_ERROR = "-41004";
    /**
     * 数据不正确
     */
    private final static String NO_DATA = "-41005";
    private final static int IV_LENGTH = 24;
    private final static int KEY_LENGTH = 24;

    private String appid;
    private String sessionKey;

    public WxBizDataCrypt(String appid, String sessionKey) {
        this.appid = appid;
        this.sessionKey = sessionKey;
    }

    /**
     * 检验数据的真实性，并且获取解密后的明文.
     * @param encryptedData string 加密的用户数据
     * @param iv            string 与用户数据一同返回的初始向量
     * @return String 返回用户信息
     */
    public String decryptData(String encryptedData, String iv) {
        if (StringUtils.length(sessionKey) != KEY_LENGTH) {
            throw new WxAesException("非法密钥", ILLEGAL_AES_KEY);
        }
        // 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
        byte[] aesKey = Base64.getDecoder().decode(sessionKey);
        if (StringUtils.length(iv) != IV_LENGTH) {
            throw new WxAesException("非法初始向量", ILLEGAL_IV);
        }
        // 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
        byte[] aesIV = Base64.getDecoder().decode(iv);
        // 对称解密的目标密文为 Base64_Decode(encryptedData)
        byte[] aesCipher = Base64.getDecoder().decode(encryptedData);
        byte[] resultByte = WxAesUtils.decrypt(aesCipher, aesKey, aesIV);
        if (null != resultByte && resultByte.length > 0) {
            String userInfo = new String(resultByte, StandardCharsets.UTF_8);
            JSONObject jsons = JSON.parseObject(userInfo);
            String id = jsons.getJSONObject("watermark").getString("appid");
            if (!StringUtils.equals(id, appid)) {
                throw new WxAesException("非法密文", ILLEGAL_BUFFER);
            }
            return userInfo;
        }
        throw new WxAesException("数据不正确", NO_DATA);
    }

    /**
     * encryptedData 和 iv 两个参数通过小程序wx.getUserInfo()方法获取
     * @param args args
     * @author ymzhu
     * @date 2019/3/26 17:09
     */
    public static void main(String[] args) {
        String appId = "wx4f4bc4dec97d474b";
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM"
                + "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS"
                + "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+"
                + "3hVbJSRgv+4lGOETKUQz6OYStslQ142d"
                + "NCuabNPGBzlooOmB231qMM85d2/fV6Ch"
                + "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6"
                + "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw"
                + "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn"
                + "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs"
                + "8LOddcQhULW4ucetDf96JcR3g0gfRK4P"
                + "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB"
                + "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns"
                + "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd"
                + "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV"
                + "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG"
                + "20f0a04COwfneQAGGwd5oa+T8yO5hzuy"
                + "Db/XcxxmK01EpqOyuxINew==";
        String iv = "r7BXXKkLb8qrSNn05n0qiA==";
        WxBizDataCrypt biz = new WxBizDataCrypt(appId, sessionKey);
        System.out.println(biz.decryptData(encryptedData, iv));
    }
}
