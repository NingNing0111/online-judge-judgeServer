package com.ningning0111.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: com.ningning0111.model
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/2 18:16
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JudgeVo implements Serializable {

    /**
     * 测试用例的输入数据
     */
    private List<String> inputData;
    /**
     * 用户输出的结果
     */
    private List<String> userOutput;
    /**
     * 用例是否通过
     */
    private List<Boolean> passInfo;
    /**
     * 测试用例的数量
     */
    private Integer count;
    /**
     * 执行代码详情信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题结果
     */
    private String status;
}
