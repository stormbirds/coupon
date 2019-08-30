package cn.stormbirds.coupon.base;

import java.io.Serializable;

/**
 * <p> ResultJson.java
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/2 17:23
 */
public class ResultJson<T> implements Serializable {


    private static final long serialVersionUID = 7488995406331282790L;
    private int code;
    private String msg;
    private T data;

    public static ResultJson<Object> ok() {
        return ok("");
    }

    public static ResultJson<Object> ok(Object o) {
        return new ResultJson<>(ResultCode.SUCCESS, o);
    }

    public static ResultJson<Object> failure(ResultCode code) {
        return failure(code, "");
    }

    public static ResultJson<Object> failure(ResultCode code, Object o) {
        return new ResultJson<>(code, o);
    }

    public ResultJson(ResultCode resultCode) {
        setResultCode(resultCode);
    }

    public ResultJson(ResultCode resultCode, T data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"msg\":\"" + msg + '\"' +
                ", \"data\":\"" + data + '\"' +
                '}';
    }
}