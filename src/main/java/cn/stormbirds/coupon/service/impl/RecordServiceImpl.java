package cn.stormbirds.coupon.service.impl;

import cn.stormbirds.coupon.base.ResultCode;
import cn.stormbirds.coupon.base.ResultJson;
import cn.stormbirds.coupon.entity.CouponRecord;
import cn.stormbirds.coupon.entity.PromotionRecord;
import cn.stormbirds.coupon.exception.CustomControllerException;
import cn.stormbirds.coupon.mapper.RecordMapper;
import cn.stormbirds.coupon.service.IPromotionRecordService;
import cn.stormbirds.coupon.service.IRecordService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 优惠券使用记录表 服务实现类
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Slf4j
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, CouponRecord> implements IRecordService {

    @Autowired
    private IPromotionRecordService promotionRecordService;

    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2000, 1, 1, 0, 0);

    @Override
    public List<CouponRecord> getRecordsByShopId(Long promotionId) {
        return list(Wrappers.<CouponRecord>lambdaQuery().eq(CouponRecord::getPromotionId, promotionId));
    }

    @Override
    public ResultJson addRecord(Long promotionId, Long userId, int count) {
        PromotionRecord promotionRecord = promotionRecordService.getById(promotionId);
        if (promotionRecord == null) {
            log.error("未找到当前活动 promotionId = {}", promotionId);
            throw new CustomControllerException(ResultJson.failure(ResultCode.NOT_FOUND, "未找到当前活动"));
        }
        int couponRecordCount = promotionRecord.getIssuedCount();
        if (!promotionRecord.getEnable()) {
            log.info("当前活动已关闭 promotionId = {}", promotionId);
            throw new CustomControllerException(ResultJson.failure(ResultCode.FORBIDDEN, "当前活动已关闭"));
        } /*else if (LocalDateTime.now().isBefore(promotionRecord.getValidStartTime())) {
            log.info("当前活动还未开始 promotionId = {}", promotionId);
            throw new CustomControllerException(ResultJson.failure(ResultCode.FORBIDDEN, "当前活动还未开始"));
        }*/ else if (LocalDateTime.now().isAfter(promotionRecord.getInvalidTime())) {
            log.info("当前活动已结束 promotionId = {}", promotionId);
            throw new CustomControllerException(ResultJson.failure(ResultCode.FORBIDDEN, "当前活动已结束"));
        } else if (couponRecordCount >= promotionRecord.getCouponCount()) {
            log.info("当前活动优惠券已用完 promotionId = {}", promotionId);
            throw new CustomControllerException(ResultJson.failure(ResultCode.FORBIDDEN, "当前活动优惠券已用完"));
        } else if ((couponRecordCount + count) >= promotionRecord.getCouponCount()) {
            log.info("当前活动优惠券剩余 {} 张，不足请求的 {} 张， promotionId = {}", promotionRecord.getCouponCount() - couponRecordCount, count, promotionId);
            throw new CustomControllerException(ResultJson.failure(ResultCode.FORBIDDEN,
                    String.format("当前活动优惠券剩余 %s 张，不足请求的 %s 张",
                            promotionRecord.getCouponCount() - couponRecordCount, count)));
        }
        log.error("生成时间 {}",LocalDateTime.now());
        List<CouponRecord> records = Stream.iterate(0, i -> i++)
                .limit(count)
                .map(integer -> CouponRecord.builder()
                        .createdAt(LocalDateTime.now())
                        .receiveUserId(userId)
                        .promotionId(promotionId)
                        .usedAt(defaultLocalDateTime)
                        .usedShopId(0L)
                        .used(false)
                        .userId(0L)
                        .couponCode(UUID.randomUUID().toString().replace("-", "").toUpperCase())
                        .build()).collect(Collectors.toList());
        if (saveBatch(records)) {
            promotionRecord.setIssuedCount(couponRecordCount+count);
            promotionRecordService.updateById(promotionRecord);
            return ResultJson.ok(records.stream().map(CouponRecord::getCouponCode).collect(Collectors.toList()));
        }
        return ResultJson.failure(ResultCode.SERVER_ERROR, "保存数据出错，请重试");
    }

    @Override
    public boolean usedCoupon(String couponCode, Long shopId, Long userId) {
        List<CouponRecord>  records = list(Wrappers.<CouponRecord>lambdaQuery().eq(CouponRecord::getCouponCode,couponCode)) ;
        if(records.size()<=0){
            log.error("未找到当前活动 couponCode = {}", couponCode);
            throw new CustomControllerException(ResultJson.failure(ResultCode.NOT_FOUND, "未找到当前活动"));
        }
        PromotionRecord promotionRecord = promotionRecordService.getById(records.get(0).getPromotionId());
        if (promotionRecord == null) {
            log.error("未找到当前活动 couponCode = {}", couponCode);
            throw new CustomControllerException(ResultJson.failure(ResultCode.NOT_FOUND, "未找到当前活动"));
        }else if (!promotionRecord.getEnable()) {
            log.info("当前活动已关闭 couponCode = {}", couponCode);
            throw new CustomControllerException(ResultJson.failure(ResultCode.FORBIDDEN, "当前活动已关闭"));
        } else if (LocalDateTime.now().isBefore(promotionRecord.getValidStartTime())) {
            log.info("当前活动还未开始 couponCode = {}", couponCode);
            throw new CustomControllerException(ResultJson.failure(ResultCode.FORBIDDEN, "当前活动还未开始"));
        } else if (LocalDateTime.now().isAfter(promotionRecord.getInvalidTime())) {
            log.info("当前活动已结束 couponCode = {}", couponCode);
            throw new CustomControllerException(ResultJson.failure(ResultCode.FORBIDDEN, "当前活动已结束"));
        }

        return update(Wrappers.<CouponRecord>lambdaUpdate()
                .eq(CouponRecord::getCouponCode, couponCode)
                .eq(CouponRecord::getUsed, false)
                .set(CouponRecord::getUsedShopId, shopId)
                .set(CouponRecord::getUsedAt, LocalDateTime.now())
                .set(CouponRecord::getUserId,userId)
                .set(CouponRecord::getUsed, true));
    }

}
