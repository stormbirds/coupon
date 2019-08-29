package cn.stormbirds.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券使用记录表
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon_record")
@ApiModel(value="CouponRecord对象", description="优惠券使用记录表")
public class CouponRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "优惠券ID")
    private Long couponId;

    @ApiModelProperty(value = "优惠券使用者ID")
    private Long userId;

    @ApiModelProperty(value = "优惠券使用时间")
    private LocalDateTime createdAt;


}
