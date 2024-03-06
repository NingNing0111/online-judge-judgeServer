package com.ningning0111.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ningning0111.api.JudgeMachineApi;
import com.ningning0111.api.TestCaseApi;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.model.dto.JudgeRequest;
import com.ningning0111.model.dto.JudgeConfig;
import com.ningning0111.model.entity.Submission;
import com.ningning0111.model.entity.TestCase;
import com.ningning0111.model.enums.SubmissionStatus;
import com.ningning0111.model.vo.JudgeVo;
import com.ningning0111.repository.SubmissionRepository;
import com.ningning0111.service.JudgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/5 13:14
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {
    private final SubmissionRepository submissionRepository;
    private final JudgeMachineApi judgeMachineApi;
    private final TestCaseApi testCaseApi;

    @Override
    public void judge(Submission submission) {

        // 对同一个提交记录修改判题状态
        submission.setStatus(SubmissionStatus.RUNNING.getValue());
        submissionRepository.save(submission);
        log.error("开始判题：{}",submission.getId());
        // 开始判题
        try {
            // 1. 获取测试用例
            BaseResponse<TestCase> testCaseContent = testCaseApi.getTestCaseByQuestionId(submission.getQuestionId());

            JudgeConfig judgeConfig = new JudgeConfig();
            JudgeRequest request = JudgeRequest.builder()
                    .config(judgeConfig)
                    .inputData(testCaseContent.getData().getInputData())
                    .outputData(testCaseContent.getData().getOutputData())
                    .code(submission.getCode())
                    .config(JSON.parseObject(submission.getJudgeConfig(), JudgeConfig.class))
                    .language(submission.getLanguage())
                    .questionId(submission.getQuestionId())
                    .userId(submission.getUserId())
                    .build();

            BaseResponse<JudgeVo> judge = judgeMachineApi.judge(request);

            // 判题失败
            if (judge.getCode() == ErrorCode.SYSTEM_ERROR.getCode()) {
                submission.setStatus(SubmissionStatus.FAILED.getValue());
                log.error("判题失败：{}",submission.getId());
                submissionRepository.save(submission);
                return;
            }
            // 判题完成
            submission.setStatus(SubmissionStatus.SUCCEED.getValue());
            JudgeVo data = judge.getData();
            submission.setJudgeInfo(JSON.toJSONString(data.getJudgeInfo()));
            submission.setInput(data.getInputData());
            submission.setOutput(data.getUserOutput());
            submission.setPassInfo(data.getPassInfo());
            submission.setStatus(SubmissionStatus.SUCCEED.getValue());
            submissionRepository.save(submission);
            log.error("判题成功：{}",submission.getId());


        } catch (Exception e) {
            submission.setStatus(SubmissionStatus.FAILED.getValue());
            submissionRepository.save(submission);
            log.error("判题异常：{}",submission.getId());

        }

    }
}
