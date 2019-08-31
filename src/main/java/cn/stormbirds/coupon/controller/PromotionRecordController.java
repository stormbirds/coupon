package cn.stormbirds.coupon.controller;


import cn.stormbirds.coupon.base.CustomApiResponses;
import cn.stormbirds.coupon.base.ResultCode;
import cn.stormbirds.coupon.base.ResultJson;
import cn.stormbirds.coupon.entity.PromotionRecord;
import cn.stormbirds.coupon.service.IPromotionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 优惠券活动记录 前端控制器
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Api(value = "优惠券活动记录 前端控制器", tags = {"商家活动操作接口"})
@RestController
@RequestMapping("/api/v1")
public class PromotionRecordController {
    @Autowired
    private IPromotionRecordService promotionRecordService;

    @ApiOperation(value = "获取所有活动记录")
    @GetMapping(value = "/coupon-promotion-record")
    @CustomApiResponses
    public ResultJson getPromotionRecords() {
        return ResultJson.ok(promotionRecordService.list()) ;
    }

    @ApiOperation(value = "根据活动id查询商家活动")
    @GetMapping(value = "/coupon-promotion-record/{id}")
    @CustomApiResponses
    public ResultJson getPromotionRecord(@PathVariable Long id) {
        PromotionRecord record = promotionRecordService.getById(id);
        if(record!=null){
            return ResultJson.ok(record);
        }
        return ResultJson.failure(ResultCode.NOT_FOUND);
    }

    @ApiOperation(value = "添加新商家优惠券活动")
    @PostMapping(value = "/coupon-promotion-record")
    @CustomApiResponses
    public ResultJson addPromotionRecord(@RequestBody PromotionRecord record) {
        if(promotionRecordService.save(record)){
            return ResultJson.ok(record);
        }
        return ResultJson.failure(ResultCode.BAD_REQUEST);
    }

    @ApiOperation(value = "更新商家优惠券活动")
    @ResponseStatus
    @PutMapping(value = "/coupon-promotion-record")
    @CustomApiResponses
    public ResultJson updatePromotionRecord(@RequestBody PromotionRecord record) {
        if (promotionRecordService.updateById(record)) {
            return ResultJson.ok(record) ;
        } else {
            return ResultJson.failure(ResultCode.OPERATE_ERROR);
        }

    }

    @ApiOperation(value = "删除商家优惠券活动")
    @DeleteMapping(value = "/coupon-promotion-record/{id}")
    @CustomApiResponses
    public ResultJson deletePromotion(@PathVariable Long id) {
        if (promotionRecordService.removeById(id)) {
            return ResultJson.ok();
        }
        return ResultJson.failure(ResultCode.OPERATE_ERROR, "操作失败");
    }
}
