package com.mjoys.common.wolf.model;

import java.util.ArrayList;
import java.util.List;

public class ReturnValue<T> {

    private boolean successful;
    private String  msg;
    private T       value;

    public static <T> ReturnValue<T> successResult(T value) {
        ReturnValue returnValue = new ReturnValue();
        returnValue.setValue(value);
        returnValue.setSuccessful(true);
        return returnValue;
    }

	public static <T> ReturnValue<T> failResult(String msg) {
		ReturnValue returnValue = new ReturnValue();
		returnValue.setMsg(msg);
		return returnValue;
	}

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
