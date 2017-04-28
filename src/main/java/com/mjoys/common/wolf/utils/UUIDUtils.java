package com.mjoys.common.wolf.utils;

import java.util.UUID;

/**
 * Created by wenqi.huang on 16/6/16.
 */
public class UUIDUtils {
    /**
     * 生成一个32位uuid
     * @return
     */
    public static String createUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
