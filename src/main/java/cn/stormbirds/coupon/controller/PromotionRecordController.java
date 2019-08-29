package cn.stormbirds.coupon.controller;


import cn.stormbirds.coupon.entity.PromotionRecord;
import cn.stormbirds.coupon.service.IPromotionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券活动记录 前端控制器
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Api(value = "优惠券活动记录 前端控制器")
@RestController
@RequestMapping("/api/vi")
public class PromotionRecordController {
    @Autowired
    private IPromotionRecordService promotionRecordService;

    @ApiOperation(value = "获取所有活动记录")
    @GetMapping(value = "/coupon-promotion-record")
    public List<PromotionRecord> getPromotionRecords(){
        return promotionRecordService.list();
    }

    @ApiOperation(value = "根据活动id查询商家活动")
    @GetMapping(value = "/coupon-promotion-record/{id}")
    public PromotionRecord getPromotionRecord(@PathVariable Long id){
        return promotionRecordService.getById(id);
    }

    @ApiOperation(value = "添加新商家优惠券活动")
    @PostMapping(value = "/coupon-promotion-record")
    public PromotionRecord addPromotionRecord(@RequestBody PromotionRecord record){
        promotionRecordService.saveOrUpdate(record);
        return record;
    }
}
