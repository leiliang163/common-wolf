package com.mjoys.common.wolf.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;

/**
 * 提供加解密等方法
 * @author wenqi.huang on 16/4/11.
 */
public final class SecurityUtils {

    private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 用SHA(即SHA1)算法编码成字节数组
     * 
     * @param src 待编码数据
     * @return 编码后字节数组
     */
    public static byte[] encode2BytesBySHA(String src) {
        return DigestUtils.sha(src);
    }

    /**
     * 用SHA算法编码成字符串
     * 
     * @param src 待加密文本
     * @return 加密后文本
     */
    public static String encode2StringBySHA(String src) {
        return DigestUtils.shaHex(src.getBytes(DEFAULT_CHARSET));
    }

    /**
     * 针对传入数据进行MD5加密
     * 
     * @param src 待编码数据
     * @return 加密后字符串
     */
    public static String encode2StringByMd5(String src) {
        return DigestUtils.md5Hex(src.getBytes(DEFAULT_CHARSET));
    }

    /**
     * 把字节数组编码成base64字符串
     * @param bytes
     * @return
     */
    public static String encode2StringByBase64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 把base64字节数组进行解码
     * @param bytes
     * @return
     */
    public static byte[] decodeBase64(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

    /**
     * 把base64字符串进行解码
     * @param src
     * @return
     */
    public static byte[] decodeBase64(String src) {
        return Base64.decodeBase64(src);
    }

}
