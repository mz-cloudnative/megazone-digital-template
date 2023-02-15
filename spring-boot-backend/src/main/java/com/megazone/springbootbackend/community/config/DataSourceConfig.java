package com.megazone.springbootbackend.community.config;

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
 * <li>설     명 : DB 이중화</li>
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
    return new LazyConnectionDataSourceProxy(routingDataSource);
  }

}
