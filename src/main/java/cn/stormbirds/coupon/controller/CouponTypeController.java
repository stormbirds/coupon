package cn.stormbirds.coupon.controller;


import cn.stormbirds.coupon.base.CustomApiResponses;
import cn.stormbirds.coupon.base.ResultJson;
import cn.stormbirds.coupon.entity.CouponType;
import cn.stormbirds.coupon.service.ITypeService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 优惠券类型表 前端控制器
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Api(value = "优惠券类型", tags = {"优惠券类型操作接口"})
@RestController
@RequestMapping("/api/v1")
public class CouponTypeController {
    private final ITypeService typeService;

    @Autowired
    public CouponTypeController(ITypeService typeService) {
        this.typeService = typeService;
    }

    @ApiOperation(value = "获取优惠券所有类型")
    @GetMapping(value = "/coupon-type")
    @CustomApiResponses
    public ResultJson getCouponTypes() {
        return ResultJson.ok(typeService.list()) ;
    }
}
