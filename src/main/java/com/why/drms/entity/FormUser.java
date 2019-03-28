package com.why.drms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月28日 12:20
 * @desc FormUser
 */
@Data
@ToString
@NoArgsConstructor
public class FormUser implements Serializable {
    private static final long serialVersionUID = -7809219004003477132L;
    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 职工名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 管辖区域
     */
    private Integer floorId;

    /**
     * 角色
     */
    private String role;

    /**
     * 校区
     */
    private Integer campusId;
}
