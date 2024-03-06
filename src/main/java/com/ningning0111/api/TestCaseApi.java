package com.ningning0111.api;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.config.JudgeServerConfig;
import com.ningning0111.model.entity.TestCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Project: com.ningning0111.api
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/5 10:42
 * @Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TestCaseApi {
    private final RestTemplate restTemplate;
    private final JudgeServerConfig config;

    public BaseResponse<TestCase> getTestCaseByQuestionId(Long questionId){
        String testCaseUrl = config.getTestCaseUrl() + "/case/" + questionId;
        ResponseEntity<BaseResponse<TestCase>> response = restTemplate.exchange(
                testCaseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BaseResponse<TestCase>>() {}
        );
        return response.getBody();
    }
}
