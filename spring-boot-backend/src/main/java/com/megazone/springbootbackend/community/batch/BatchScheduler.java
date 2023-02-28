package com.megazone.springbootbackend.community.batch;

import com.megazone.springbootbackend.community.batch.BatchConfig;
import com.megazone.springbootbackend.community.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class BatchScheduler {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private BatchConfig batchConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "* * */3 * * *")
    public void runJob(){
//        // job parameter 설정
//        Map<String, JobParameter> confMap = new HashMap<>();
//        confMap.put("time", new JobParameter(System.currentTimeMillis()));
//        JobParameters jobParameters = new JobParameters(confMap);
//
//        try {
//            jobLauncher.run(batchConfig.job(), jobParameters);
//            System.out.println("===================job 스케쥴러 동작 확인용=====================");
//        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
//                 | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
//
//            log.error(e.getMessage());
//        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void deleteViewCntCacheFromRedis() {
        Set<String> redisKeys = redisTemplate.keys("articleViewCnt*");
        Iterator<String> it = redisKeys.iterator();
        while (it.hasNext()) {
            String data = it.next();

            System.out.println(data);
            System.out.println(data.split("::")[1]);
            System.out.println(String.valueOf(data.split("::")[1].charAt(1)));

            Long articleId = Long.parseLong(String.valueOf(data.split("::")[1].charAt(1)));
            int viewCnt = Integer.parseInt((String) redisTemplate.opsForValue().get(data));
            System.out.println(viewCnt);
            articleRepository.addHit(articleId, viewCnt);
            redisTemplate.delete(data);
            redisTemplate.delete("article::"+articleId);
        }
    }
}
