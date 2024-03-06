package com.ningning0111;

import com.alibaba.fastjson2.JSON;
import com.ningning0111.api.TestCaseApi;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.constant.RabbitQueue;
import com.ningning0111.model.dto.JudgeConfig;
import com.ningning0111.model.entity.Submission;
import com.ningning0111.model.entity.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
class OjLuddJudgeApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private TestCaseApi testCaseApi;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        rabbitTemplate.setMessageConverter(messageConverter);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        Queue queue = new Queue(RabbitQueue.JUDGE_QUEUE);
        rabbitAdmin.declareQueue(queue);
        Submission sub = new Submission();
        sub.setCode("import java.util.Scanner;\n" +
                "\n" +
                "/**\n" +
                " * @Project: com.ningning0111\n" +
                " * @Author: pgthinker\n" +
                " * @GitHub: https://github.com/ningning0111\n" +
                " * @Date: 2024/3/6 16:53\n" +
                " * @Description:\n" +
                " */\n" +
                "public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner scanner = new Scanner(System.in);\n" +
                "            int a = scanner.nextInt();\n" +
                "            int b = scanner.nextInt();\n" +
                "            System.out.println(a + b);\n" +
                "    }\n" +
                "}\n");
        sub.setUserId(1L);
        sub.setLanguage("java");
        sub.setQuestionId(1001L);
        sub.setJudgeInfo("");
        sub.setJudgeConfig(JSON.toJSONString(JudgeConfig.builder()
                .maxTime(2000L)
                .maxMemory(100000000L)
                .build()));
        sub.setStatus(0);
        sub.setCreateTime(new Date(System.currentTimeMillis()));
        sub.setUpdateTime(new Date(System.currentTimeMillis()));
        sub.setIsDelete(0);
        sub.setId(1L);
        rabbitTemplate.convertAndSend(RabbitQueue.JUDGE_QUEUE,sub);
        sub.setId(2L);
        rabbitTemplate.convertAndSend(RabbitQueue.JUDGE_QUEUE,sub);
    }

    @Test
    public void test2(){
        BaseResponse<TestCase> resp =
                testCaseApi.getTestCaseByQuestionId(1001L);
        System.out.println(resp);
    }

}
