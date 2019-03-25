package com.why.drms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("repair")
public class RepairEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报修编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 报修宿舍
     */
    @TableField("room_id")
    private Integer roomId;

    /**
     * 描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 删除
     */
    @TableField("status")
    private Integer status;

    /**
     * 上报
     */
    @TableField("report")
    private Integer report;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 类型（1，自然报修，2人为报修）
     */
    @TableField("type")
    private Integer type;

    /**
     * 负责人及描述
     */
    @TableField("principal")
    private String principal;

    /**
     * 结案
     */
    @TableField("complete")
    private Integer complete;

    /**
     * 宿舍楼名称
     */
    @TableField("floor_name")
    private String floorName;

    /**
     * 宿舍名称
     */
    @TableField("room_name")
    private String roomName;

    /**
     * 宿舍楼编号
     */
    @TableField("floor_id")
    private Integer floorId;


}
