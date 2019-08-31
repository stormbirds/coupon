package cn.stormbirds.coupon.base;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses({
        @ApiResponse(code = 200,message = "成功！"),
        @ApiResponse(code = 401,message = "未授权，或者权限不足！"),
        @ApiResponse(code = 404,message = "请求的资源不存在！"),
        @ApiResponse(code = 403,message = "禁止访问！"),
        @ApiResponse(code = 405,message = "操作失败，请求操作的资源不存在！"),
        @ApiResponse(code = 408,message = "请求超时！"),
        @ApiResponse(code = 500,message = "服务器内部错误！")
})
public @interface CustomApiResponses {
}
