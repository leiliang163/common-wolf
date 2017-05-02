/**
 * 文件名： Result.java 此类描述的是： 作者: leiliang 创建时间: 2016年3月23日 上午11:00:27
 */
package com.mjoys.common.wolf.model;


import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/14 15:59.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class Result<T> implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1467576157657126613L;

    /** The code. */
    private String            code;

    /** The message. */
    private String            desc;

    /** The t. */
    private transient T       data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
