package com.mjoys.common.wolf.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Blowfish加密解密工具类
 * 
 * @link http://www.schneier.com/blowfish.html
 * @author zhoufang
 *
 */
public class BlowfishUtils {

    private static final String CIPHER_NAME="Blowfish/CFB8/NoPadding";
    private static final String KEY_SPEC_NAME="Blowfish";

    private static final ThreadLocal<HashMap<String, BlowfishUtils>> pool=new ThreadLocal<HashMap<String,BlowfishUtils>>();

    private Cipher enCipher;
    private Cipher deCipher;

    private String key;

    private BlowfishUtils(String key){
        try {
            this.key=key;
            String iv= StringUtils.substring(DigestUtils.md5Hex(key), 0,8);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key.getBytes(), KEY_SPEC_NAME);
            IvParameterSpec ivParameterSpec=new IvParameterSpec(iv.getBytes());
            enCipher=Cipher.getInstance(CIPHER_NAME);
            deCipher=Cipher.getInstance(CIPHER_NAME);
            enCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParameterSpec);
            deCipher.init(Cipher.DECRYPT_MODE, secretKeySpec,ivParameterSpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 加密
     * @param str 需要加密的字符串
     * @param key 加解密用的密钥
     * @return
     */
    public static String encryptBlowfish(String str,String key){
        return getInstance(key).encrypt(str);
    }

    /**
     * 解密
     * @param str 需要解密的字符串
     * @param key 加解密用的密钥
     * @return
     */
    public static String decryptBlowfish(String str,String key){
        return getInstance(key).decrypt(str);
    }
    
    public static String encryptBlowfish(String s){
    	return encryptBlowfish(s, "abc");
    }
    public static String decryptBlowfish(String s){
    	return decryptBlowfish(s, "abc");
    }

    private static BlowfishUtils getInstance(String key){
        HashMap<String, BlowfishUtils> keyMap=pool.get();
        if(keyMap==null || keyMap.isEmpty()){
            keyMap=new HashMap<String, BlowfishUtils>();
            pool.set(keyMap);
        }
        BlowfishUtils instance=keyMap.get(key);
        if(instance==null || !StringUtils.equals(instance.key, key)){
            instance=new BlowfishUtils(key);
            keyMap.put(key, instance);
        }
        return instance;
    }
    /**
     * 加密
     * @param s
     * @return
     */
    private String encrypt(String s){
        String result=null;
        if(StringUtils.isNotBlank(s)){
            try {
                byte[] encrypted=enCipher.doFinal(s.getBytes());
                result=new String(Base64.encodeBase64(encrypted));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 解密
     * @param s
     * @return
     */
    private String decrypt(String s){
        String result=null;
        if(StringUtils.isNotBlank(s)){
            try {
                byte[] decrypted=Base64.decodeBase64(s);
                result=new String(deCipher.doFinal(decrypted));
            } catch (Exception e) {
                resetInstance();
                e.printStackTrace();
            }
        }
        return result;
    }

    private void resetInstance(){
        pool.set(null);
    }

    public static void main(String[] args){
        String str = BlowfishUtils.encryptBlowfish("iR3jSBbKqJgFnsnM","CNxgrtFG2nYQUfu");
        System.out.println(str);
        str = BlowfishUtils.decryptBlowfish("Jks6qkHWorS47WyXCJsUdP","CNxgrtFG2nYQUfu");
        System.out.println(str);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, -18);
        System.out.println(c.getTime());
    }
}
