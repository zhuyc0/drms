package com.why.drms.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@TableName("student")
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生名称
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 班级
     */
    @TableField("clasz")
    private String clasz;

    /**
     * 出生日期
     */
    @TableField("birthday")
    private String birthday;

    /**
     * 校区编号
     */
    @TableField("campus_id")
    private Integer campusId;

    /**
     * 宿舍楼编号
     */
    @TableField("floor_id")
    private Integer floorId;

    /**
     * 宿舍编号
     */
    @TableField("room_id")
    private Integer roomId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 学生状态
     */
    @TableField("status")
    @TableLogic
    private Integer status;

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
     * 学籍号
     */
    @TableField("stu_no")
    private String stuNo;

    /**
     * 学籍号
     */
    @TableField("campus_name")
    private String campusName;

    public StudentEntity(List<String> list) {
        this.stuNo =  list.get(0);
        this.name = list.get(1);
        this.sex = "男".equals(list.get(2))?1:0;
        this.clasz = list.get(3);
        this.birthday = list.get(4);
        this.campusName = list.get(5);
        this.campusId = Integer.valueOf(list.get(6).split("\\.")[0]);
        this.floorName = list.get(7);
        this.floorId = Integer.valueOf(list.get(8).split("\\.")[0]);
        this.roomName =list.get(9);
        this.roomId = Integer.valueOf(list.get(10).split("\\.")[0]);
    }
}
