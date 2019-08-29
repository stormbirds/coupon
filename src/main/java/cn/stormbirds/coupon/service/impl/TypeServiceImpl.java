package cn.stormbirds.coupon.service.impl;

import cn.stormbirds.coupon.entity.CouponType;
import cn.stormbirds.coupon.mapper.TypeMapper;
import cn.stormbirds.coupon.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券类型表 服务实现类
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, CouponType> implements ITypeService {

}
