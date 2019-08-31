package cn.stormbirds.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>
 * 优惠券使用记录表
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("coupon_record")
@Builder
@ApiModel(value="CouponRecord对象", description="优惠券使用记录表")
public class CouponRecord implements Serializable {

    private static final long serialVersionUID = 7405219890410941612L;

    @ApiModelProperty(value = "id主键",example = "0")
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "优惠券所属活动ID",example = "0")
    @TableField(value = "promotion_id")
    private Long promotionId;

    @ApiModelProperty(value = "优惠券使用者ID",example = "10")
    @TableField(value = "user_id")
    private Long userId;

    @ApiModelProperty(value = "优惠券领取时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "created_at",jdbcType = JdbcType.DATETIMEOFFSET)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "优惠券领取者ID",example = "0")
    @TableField(value = "receive_user_id")
    private Long receiveUserId;

    @ApiModelProperty(value = "优惠券使用时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "used_at")
    private LocalDateTime usedAt;

    @ApiModelProperty(value = "优惠券使用地点",example = "0")
    @TableField(value = "used_shop_id")
    private Long usedShopId;

    @ApiModelProperty(value = "优惠券是否已使用")
    @TableField(value = "used")
    private Boolean used;

    @ApiModelProperty(value = "优惠券码，发放给用户使用")
    @TableField(value = "coupon_code",jdbcType = JdbcType.VARCHAR)
    private String couponCode;
}
