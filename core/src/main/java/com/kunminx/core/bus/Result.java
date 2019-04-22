package com.kunminx.core.bus;

/**
 * @author KunMinX
 * Create at 2018/8/22
 */
public class Result {

    private Object resultCode;
    private Object resultObject;
    private String tag;
    private boolean success;

    public Result(Object resultCode, Object resultObject) {
        this.resultCode = resultCode;
        this.resultObject = resultObject;
    }

    public Result(Object resultCode, Object resultObject, String tag) {
        this(resultCode, resultObject);
        this.tag = tag;
    }

    public Result(Object resultCode, Object resultObject, boolean success) {
        this(resultCode, resultObject);
        this.success = success;
    }

    public Result(Object resultCode, Object resultObject, String tag, boolean success) {
        this(resultCode, resultObject);
        this.tag = tag;
        this.success = success;
    }

    public Object getResultCode() {
        return resultCode;
    }

    public void setResultCode(Object resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
