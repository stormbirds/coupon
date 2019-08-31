package cn.stormbirds.coupon.service.impl;

import cn.stormbirds.coupon.entity.PromotionRecord;
import cn.stormbirds.coupon.mapper.PromotionRecordMapper;
import cn.stormbirds.coupon.service.IPromotionRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券活动记录 服务实现类
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Service
public class PromotionRecordServiceImpl extends ServiceImpl<PromotionRecordMapper, PromotionRecord> implements IPromotionRecordService {

    @Override
    public boolean save(PromotionRecord entity) {
        entity.setId(null);
        entity.setIssuedCount(0);
        entity.setCreatedAt(LocalDateTime.now());
        return super.save(entity);
    }
}
