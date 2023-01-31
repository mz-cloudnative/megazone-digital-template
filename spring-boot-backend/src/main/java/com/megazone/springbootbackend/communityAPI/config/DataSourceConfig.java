package com.megazone.springbootbackend.communityAPI.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

    private static final String PRIMARY = "primary";
    private static final String SECONDARY = "secondary";

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource-read-only.url}")
    private String readOnlyJdbcUrl;

  @Bean(name = "dataSource")
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().url(jdbcUrl).type(HikariDataSource.class).build();
  }

  @Bean(name = "readOnlyDataSource")
  @ConfigurationProperties(prefix = "spring.datasource-read-only")
  public DataSource readOnlyDataSource() {
    return DataSourceBuilder.create().url(readOnlyJdbcUrl).type(HikariDataSource.class).build();
  }

  @Bean(name = "routingDataSource")
  public DataSource routingDataSource(
          @Qualifier("dataSource") DataSource dataSource,
          @Qualifier("readOnlyDataSource") DataSource readOnlyDataSource) {
      AbstractRoutingDataSource routingDataSource =
              new AbstractRoutingDataSource() {
                  @Override
                  protected Object determineCurrentLookupKey() {
                      return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                              ? SECONDARY
                              : PRIMARY;
                  }
              };
      Map<Object, Object> dataSourceMap = new HashMap<>();
      dataSourceMap.put(SECONDARY, readOnlyDataSource);
      dataSourceMap.put(PRIMARY, dataSource);
      routingDataSource.setTargetDataSources(dataSourceMap);
      routingDataSource.setDefaultTargetDataSource(dataSource);
      return routingDataSource;
  }
  @Primary
  @Bean
  public DataSource lazyConnectionDataSource(
          @Qualifier("routingDataSource") DataSource routingDataSource) {
      return new LazyConnectionDataSourceProxy(routingDataSource);
  }
}
