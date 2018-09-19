package com.kunminx.architecture.business.bus;

/**
 * @author KunMinX
 * @date 2018/8/22
 */
public class Result {

    private int resultCode;
    private Object resultObject;

    public Result(int resultCode, Object resultObject) {
        this.resultCode = resultCode;
        this.resultObject = resultObject;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }

}
