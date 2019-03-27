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
@TableName("dormitory_floor")
public class DormitoryFloorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 宿舍楼编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 宿舍楼名称
     */
    @TableField("floor_name")
    private String floorName;

    /**
     * 男女宿舍
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 校区编号
     */
    @TableField("campus_id")
    private Integer campusId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 删除
     */
    @TableField("status")
    @TableLogic
    private Integer status;

    /**
     * 开放
     */
    @TableField("open")
    private Integer open;


}
