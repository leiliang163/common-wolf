package com.mjoys.common.wolf.model;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/14 15:59.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class ReturnValue<T> {

    /**
     * The Successful.
     */
    private boolean successful;
    /**
     * The Msg.
     */
    private String  msg;
    /**
     * The Value.
     */
    private T       value;

    /**
     * Success result return value.
     *
     * @param <T>   the type parameter
     * @param value the value
     *
     * @return the return value
     */
    public static <T> ReturnValue<T> successResult(T value) {
        ReturnValue returnValue = new ReturnValue();
        returnValue.setValue(value);
        returnValue.setSuccessful(true);
        return returnValue;
    }

    /**
     * Success result return value.
     *
     * @return the return value
     */
    public static  ReturnValue<Boolean> successResult() {
        ReturnValue returnValue = new ReturnValue();
        returnValue.setValue(true);
        returnValue.setSuccessful(true);
        return returnValue;
    }

    /**
     * Fail result return value.
     *
     * @param <T> the type parameter
     * @param msg the msg
     *
     * @return the return value
     */
    public static <T> ReturnValue<T> failResult(String msg) {
		ReturnValue returnValue = new ReturnValue();
		returnValue.setMsg(msg);
		return returnValue;
	}

    /**
     * Is successful boolean.
     *
     * @return the boolean
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Sets successful.
     *
     * @param successful the successful
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(T value) {
        this.value = value;
    }
}
