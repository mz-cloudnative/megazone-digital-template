package com.megazone.springbootbackend.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : AsyncConfig</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/21</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/21 2:19 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

  @Override
  @Bean(name = {"getAsyncExecutor"})
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10); // 기본적으로 실행 대기 중인 Thread 개수
    executor.setMaxPoolSize(20); // 동시에 동작하는 최대 Thread 개수
    executor.setQueueCapacity(50); // CorePool이 초과될때 Queue에 저장했다가 꺼내서 실행된다. (50개까지 저장함)
    // 단, MaxPoolSize가 초과되면 Thread 생성에 실패할 수 있음.
    // 참고: https://medium.com/trendyol-tech/spring-boot-async-executor-management-with-threadpooltaskexecutor-f493903617d
    // jooncco님 수정사항
    executor.setThreadNamePrefix("async-"); // Spring에서 생성하는 Thread 이름의 접두사
    executor.initialize();
    return executor;
  }
}
