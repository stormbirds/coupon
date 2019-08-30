package cn.stormbirds.coupon.exception;

import cn.stormbirds.coupon.base.ResultJson;

/**
 *
 * <p> CustomControllerException.java
 * </p>
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/30 16:01
 *
 */
public class CustomControllerException extends RuntimeException{
    private static final long serialVersionUID = -8592312925888367630L;
    private ResultJson resultJson;

    public CustomControllerException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }

    public ResultJson getResultJson() {
        return resultJson;
    }
}