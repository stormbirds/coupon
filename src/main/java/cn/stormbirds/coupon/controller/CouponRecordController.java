package cn.stormbirds.coupon.controller;


import cn.stormbirds.coupon.base.CustomApiResponses;
import cn.stormbirds.coupon.base.ResultCode;
import cn.stormbirds.coupon.base.ResultJson;
import cn.stormbirds.coupon.entity.CouponRecord;
import cn.stormbirds.coupon.service.IRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券使用记录表 前端控制器
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Slf4j
@Api(value = "优惠券使用记录表 前端控制器", tags = {"优惠券操作接口"})
@RestController
@RequestMapping("/api/v1")
public class CouponRecordController {
    @Autowired
    private IRecordService recordService;

    @ApiOperation(value = "根据优惠券Id获取优惠券消费记录")
    @GetMapping(value = "/coupon-record/{promotionId}")
    @CustomApiResponses
    public ResultJson getRecordsByShopId(@PathVariable Long promotionId) {
        return ResultJson.ok(recordService.getRecordsByShopId(promotionId)) ;
    }

    @ApiOperation(value = "优惠券使用")
    @PatchMapping(value = "/coupon-record")
    @CustomApiResponses
    public ResultJson addRecord(@RequestParam String couponCode, @RequestParam Long shopId, @RequestParam Long userId) {
        if (recordService.usedCoupon(couponCode, shopId, userId)) {
            return ResultJson.ok("优惠券使用成功");
        }
        return ResultJson.failure(ResultCode.BAD_REQUEST, "优惠券无效");
    }

    @ApiOperation(value = "优惠券发放")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "promotionId", value = "活动Id", paramType = "query", example = "1167031892952084482", defaultValue = "1167031892952084482", dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "领取人Id", paramType = "query", example = "10", defaultValue = "10", dataType = "Long"),
            @ApiImplicitParam(name = "count", value = "领取数量", paramType = "query", example = "20", defaultValue = "20", dataType = "int")
    })
    @PostMapping(value = "/coupon-record")
    @CustomApiResponses
    public ResultJson addRecord(@RequestParam Long promotionId, @RequestParam Long userId, @RequestParam int count) {
        return recordService.addRecord(promotionId, userId, count);
    }
}
