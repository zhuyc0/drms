package com.why.drms.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField("password")
    private String password;

    /**
     * 职工名称
     */
    @TableField("name")
    private String name;

    /**
     * 状态
     */
    @JsonIgnore
    @TableField("status")
    @TableLogic
    private Integer status;

    /**
     * 管辖区域
     */
    @TableField("floor_id")
    private Integer floorId;

    /**
     * 角色
     */
    @TableField("role")
    private String role;

    /**
     * 校区
     */
    @TableField("campus_id")
    private Integer campusId;


}
