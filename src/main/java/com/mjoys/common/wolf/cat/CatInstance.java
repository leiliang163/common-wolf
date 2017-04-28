package com.mjoys.common.wolf.cat;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/27 14:41.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
import com.dianping.cat.Cat;

public class CatInstance {
    private static boolean isEnable = true;

    public CatInstance() {
    }

    public static void disable() {
        isEnable = false;
    }

    public static void enable() {
        isEnable = true;
    }

    public static boolean isEnable() {
        return Cat.getManager().isCatEnabled() && isEnable;
    }
}
