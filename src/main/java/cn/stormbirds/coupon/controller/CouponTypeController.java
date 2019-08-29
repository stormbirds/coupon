package cn.stormbirds.coupon.controller;


import cn.stormbirds.coupon.entity.CouponType;
import cn.stormbirds.coupon.service.ITypeService;
import com.alibaba.fastjson.JSONObject;
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
@RestController
@RequestMapping("/api/vi")
public class CouponTypeController {
    private final ITypeService typeService;

    @Autowired
    public CouponTypeController(ITypeService typeService) {
        this.typeService = typeService;
    }

    @ApiOperation(value = "获取优惠券所有类型")
    @GetMapping(value = "/coupon-type")
    public List<CouponType> getCouponTypes(){
        return typeService.list();
    }
}
