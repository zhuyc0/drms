package com.why.drms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月27日 11:59
 * @desc LayuiPage 分页信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LayuiPage {
    private Long page;
    private Long pageSize;
}
