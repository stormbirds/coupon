package cn.stormbirds.coupon.exception;

import cn.stormbirds.coupon.base.ResultCode;
import cn.stormbirds.coupon.base.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * cn.stormbirds.coupon.exception
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/30 15:59
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(CustomControllerException.class)
    public ResultJson handleCustomException(CustomControllerException e){
        log.error(e.getResultJson().getMsg());
        return e.getResultJson();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResultJson.failure(ResultCode.BAD_REQUEST,
                e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
