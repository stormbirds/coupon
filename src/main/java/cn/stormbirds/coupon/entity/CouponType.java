package cn.stormbirds.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券类型表
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon_type")
@ApiModel(value="CouponType对象", description="优惠券类型表")
public class CouponType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "活动类型名称，例如满减、免单、现金券、打折券等")
    private String typeName;

    @ApiModelProperty(value = "活动描述")
    private String typeDesc;


}
