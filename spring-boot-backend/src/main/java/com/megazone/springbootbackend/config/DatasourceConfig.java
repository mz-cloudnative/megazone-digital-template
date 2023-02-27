package com.megazone.springbootbackend.config;

import com.zaxxer.hikari.HikariDataSource;
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

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

// EnableTransactionManagement : @Transactional을 찾아서 트랜잭션의 범위를 활성화하는 기능
@Configuration
@EnableTransactionManagement
public class DatasourceConfig {
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

    @Bean(name = "routingDataSource")
    @DependsOn({"masterDataSource", "slaveDataSource"})
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource
    ) {
        AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? SLAVE : MASTER;
            }
        };

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(SLAVE, slaveDataSource);
        dataSourceMap.put(MASTER, masterDataSource);
        abstractRoutingDataSource.setTargetDataSources(dataSourceMap);
        abstractRoutingDataSource.setDefaultTargetDataSource(masterDataSource);

        return abstractRoutingDataSource;
    }

    @Primary
    @Bean(name = "dataSource")
    @DependsOn("routingDataSource")
    public DataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource) {
        return new LazyConnectionDataSourceProxy(masterDataSource);
    }
}
