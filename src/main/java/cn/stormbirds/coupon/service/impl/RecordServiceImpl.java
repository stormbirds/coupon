package cn.stormbirds.coupon.service.impl;

import cn.stormbirds.coupon.entity.CouponRecord;
import cn.stormbirds.coupon.mapper.RecordMapper;
import cn.stormbirds.coupon.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券使用记录表 服务实现类
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, CouponRecord> implements IRecordService {

}
