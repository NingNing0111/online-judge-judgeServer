package com.ningning0111.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: com.ningning0111.model
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 19:31
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCase implements Serializable {
    /**
     * 测试用例ID
     */
    private String id;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 输入数据
     */
    private List<String> inputData;

    /**
     * 输出数据
     */
    private List<String> outputData;
}
