package com.lizheng.bean.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yuanghaijiang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page implements Serializable {
    /**
     * 页大小
     */
    private Integer pageSize;
    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 总页数
     */
    private Long totalCount = 0L;

}
