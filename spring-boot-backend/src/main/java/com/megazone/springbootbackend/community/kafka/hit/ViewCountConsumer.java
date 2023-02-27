package com.megazone.springbootbackend.community.kafka.hit;

import com.megazone.springbootbackend.community.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class ViewCountConsumer {
    private final Logger logger = LoggerFactory.getLogger(ViewCountConsumer.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ArticleRepository articleRepository;

    @KafkaListener(topics = "${spring.kafka.topic.view-count}", groupId = "test-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String articleId) throws Exception {
//        //System.out.println(Long.parseLong(consumerRecord.value().toString()));
//        //System.out.println(Long.valueOf(consumerRecord.value().toString()));
//        Long views = redisTemplate.opsForValue().increment("articleViewCount"+ articleId);
//        System.out.println("Article " + articleId + " has " + views + " views.");

        String key = "articleViewCnt::"+articleId;
        //캐시에 값이 없으면 레포지토리에서 조회, 있으면 값을 증가
        ValueOperations valueOperations = redisTemplate.opsForValue();

        Long articleId2 = Long.parseLong(articleId);

        if(valueOperations.get(key)==null)
            valueOperations.set(
                    key,
                    String.valueOf(articleRepository.findHit(articleId2)),
                    Duration.ofMinutes(5));
        else
            valueOperations.increment(key);
        logger.info("value:{}",valueOperations.get(key));
    }




}