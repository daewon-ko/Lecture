package io.springbatch.springbatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class MetaDBConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-meta") // applicationProperties의 설정값 자동으로 불러옴
    public DataSource metaDBSource() {
        return DataSourceBuilder.create().build();

    }

    @Primary
    @Bean
    public PlatformTransactionManager metaTransactionManger() {
        return new DataSourceTransactionManager(metaDBSource());
    }

}
