package com.mjoys.common.wolf.model;

import java.io.Serializable;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/14 15:59.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class DubboResult<T> implements Serializable {
    private static final long serialVersionUID = -6978963188996203322L;
    /**
     * 成功响应码
     */
    private static final String SUCCESS_RETURN_CODE = "0";
    /**
     * 是否被调用到
     */
    private boolean isCalled = false;

    private String msg = "success";

    private String returnCode = SUCCESS_RETURN_CODE;

    private T result;

    public static <T> DubboResult<T> successResult(T result) {
        DubboResult dubboResult = new DubboResult();
        dubboResult.setCalled(true);
        dubboResult.setResult(result);
        return dubboResult;
    }

    public static <T> DubboResult<T> failResult(String errorCode, String msg) {
        DubboResult dubboResult = new DubboResult();
        dubboResult.setCalled(true);
        dubboResult.setReturnCode(errorCode);
        dubboResult.setMsg(msg);
        return dubboResult;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setCalled(boolean isCalled) {
        this.isCalled = isCalled;
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 调用到Dubbo接口并且接口业务处理成功(返回码为0)，代表调用处理成功
     *
     * @return
     */
    public boolean isSuccess() {
        return this.isCalled && SUCCESS_RETURN_CODE.equals(this.returnCode);
    }

}
