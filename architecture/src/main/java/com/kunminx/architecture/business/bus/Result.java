package com.kunminx.architecture.business.bus;

/**
 * @author KunMinX
 * @date 2018/8/22
 */
public class Result {

    private Object resultCode;
    private Object resultObject;

    public Result(Object resultCode, Object resultObject) {
        this.resultCode = resultCode;
        this.resultObject = resultObject;
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

}
