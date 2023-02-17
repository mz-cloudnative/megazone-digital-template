package com.megazone.springbootbackend.community.batch;

import com.megazone.springbootbackend.community.model.entity.Token;
import com.megazone.springbootbackend.community.repository.TokenRepository;
import com.megazone.springbootbackend.community.repository.UserRepository;
import com.megazone.springbootbackend.community.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;

//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private TokenRepository tokenRepository;
//
//    @Autowired
//    private AuthService authService;

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private TokenRepository tokenRepository;
    private AuthService authService;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, @Lazy TokenRepository tokenRepository,  @Lazy AuthService authService) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.tokenRepository = tokenRepository;
        this.authService = authService;
    }

    @Bean
    public Job job() {

        Job job = jobBuilderFactory.get("job")
                .start(step())
                .build();

        return job;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Step!");

                    List<Token> allTokenList = tokenRepository.findAll();
                    System.out.println("=====================job 스케쥴러 동작확인용 2=======================");
                    if (allTokenList.size() > 0 && allTokenList != null) {
                        for (Token token : allTokenList) {
                            authService.deleteExpiredToken(token);
                        }
                    }
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
