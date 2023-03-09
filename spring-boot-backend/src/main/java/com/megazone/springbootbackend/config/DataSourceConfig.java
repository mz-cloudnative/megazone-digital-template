package com.megazone.springbootbackend.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : DB 연결</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : DataSourceConfig</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/01/13</li>
 * <li>설     명 : DB 부하분산을 위한 이중화(Master/Slave) 처리</li>
 * <li>이     슈 : P6Spy(쿼리에 파라메터 바인딩하여 로그 출력)를 사용하는 경우엔 </li>
 * <li>           P6SpyDataSource로 래핑 후 P6Proxy를 만들게 되어 DataSource가 설정한대로 동작이 되지 않게 됨.</li>
 * <li>           그러므로 P6Spy는 로컬환경에서만 사용하도록 해야함.</li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/01/13 2:01 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

  private static final String MASTER = "master";
  private static final String SLAVE = "slave";

  @Bean(name = "masterDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.master.hikari")
  public DataSource masterDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean(name = "slaveDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
  public DataSource slaveDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @DependsOn({"masterDataSource", "slaveDataSource"})
  @Bean(name = "routingDataSource")
  public DataSource routingDataSource(
      @Qualifier("masterDataSource") DataSource masterDataSource,
      @Qualifier("slaveDataSource") DataSource slaveDataSource) {
    AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
      @Override
      protected Object determineCurrentLookupKey() {
        //@Transactional(readOnly = true)인 경우에만 slave를 호출하도록 한다.
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? SLAVE : MASTER;
      }
    };
    Map<Object, Object> dataSourceMap = new HashMap<>();
    dataSourceMap.put(MASTER, masterDataSource);
    dataSourceMap.put(SLAVE, slaveDataSource);
    routingDataSource.setTargetDataSources(dataSourceMap);
    routingDataSource.setDefaultTargetDataSource(masterDataSource);
    return routingDataSource;
  }

  @Primary
  @DependsOn("routingDataSource")
  @Bean(name = "dataSource")
  public DataSource dataSource(
      @Qualifier("routingDataSource") DataSource routingDataSource) {
    //Master/Slave 환경에서 트랜잭션 진입시점에 커넥션을 결정하지 않도록 하는 용도. 살제로 커넥션이 필요한 경우에만 커넥션 점유
    return new LazyConnectionDataSourceProxy(routingDataSource);
  }

}
