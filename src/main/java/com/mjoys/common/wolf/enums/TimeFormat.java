/**
 * Project Name:engine-common<br>
 * File Name:TimeFormat.java<br>
 * Package Name:cn.com.duiba.tuia.constants<br>
 * Date:2016年11月25日下午3:03:48<br>
 * Copyright (c) 2016, duiba.com.cn All Rights Reserved.<br>
 */

package com.mjoys.common.wolf.enums;

/**
 * ClassName: TimeFormat <br/>
 * Function: 时间格式. <br/>
 * date: 2016年11月25日 下午3:03:48 <br/>
 *
 * @author leiliang
 * @since JDK 1.6
 */
public enum TimeFormat {


    /**
     * yyyy-MM-dd.
     */
    YYYY_MM_DD("yyyy-MM-dd"),

    /**
     * yyyy-MM-dd HH:mm:ss.
     */
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),;

    private String format;

    private TimeFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return this.format;
    }
}
