package com.why.drms.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author why
 * @since 2019-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("campus")
public class CampusEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 校区编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 校区名称
     */
    @TableField("campus")
    private String campus;

    /**
     * 是否删除
     */
    @TableField("status")
    @TableLogic
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


}
