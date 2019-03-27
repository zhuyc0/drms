package com.why.drms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月27日 13:03
 * @desc LayuiQuery 查询实体
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LayuiQuery {

    private String name;
    private Date startDate;
    private Date endDate;
}
