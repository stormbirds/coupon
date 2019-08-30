package cn.stormbirds.coupon.service;

import cn.stormbirds.coupon.base.ResultJson;
import cn.stormbirds.coupon.entity.CouponRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠券使用记录表 服务类
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
public interface IRecordService extends IService<CouponRecord> {

    List<CouponRecord> getRecordsByShopId(Long promotionId);

    ResultJson addRecord(Long promotionId, Long userId, int count);

    boolean usedCoupon(String couponCode,Long shopId, Long userId);
}
