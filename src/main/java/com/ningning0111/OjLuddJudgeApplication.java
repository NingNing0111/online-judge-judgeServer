package com.ningning0111;

import com.ningning0111.constant.RabbitQueue;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class OjLuddJudgeApplication {

    private final RabbitTemplate rabbitTemplate;
    public static void main(String[] args) {
        SpringApplication.run(OjLuddJudgeApplication.class, args);
    }

    @PostConstruct
    public void init(){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        QueueInformation queueInfo = rabbitAdmin.getQueueInfo(RabbitQueue.JUDGE_QUEUE);
        log.info("==>{}",queueInfo);
        Queue queue = new Queue(RabbitQueue.JUDGE_QUEUE);
        rabbitAdmin.declareQueue(queue);
    }

}
