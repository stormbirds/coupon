package cn.stormbirds.coupon.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券活动记录
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PromotionRecord对象", description="优惠券活动记录")
public class PromotionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id主键，无需填写")
    private Long id;

    @ApiModelProperty(value = "商家ID",example = "1167031892952084482")
    private Long shopId;

    @ApiModelProperty(value = "活动类型满减、免单、现金券、打折券",example = "0")
    private Integer couponType;

    @ApiModelProperty(value = "优惠券张数",example = "10")
    private Integer couponCount;

    @ApiModelProperty(value = "优惠券活动描述")
    private String couponDesc;

    @ApiModelProperty(value = "优惠券活动创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "活动开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validStartTime;

    @ApiModelProperty(value = "活动失效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime invalidTime;

    @ApiModelProperty(value = "活动是否有效")
    private Boolean enable;

    @ApiModelProperty(value = "现金券金额")
    private BigDecimal moneyAmount;

    @ApiModelProperty(value = "满减活动触发条件金额")
    private BigDecimal amountTop;

    @ApiModelProperty(value = "满减活动实际执行金额")
    private BigDecimal amountReduced;

    @ApiModelProperty(value = "打折券折扣系数，限1-99之间整数，最终结果除以100",example = "90")
    private Integer discount;

    @ApiModelProperty(value = "已发放优惠券数量",example = "90")
    private Integer issuedCount;
}
