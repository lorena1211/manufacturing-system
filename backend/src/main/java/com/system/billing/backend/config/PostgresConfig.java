package com.system.billing.backend.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration 
@EnableJpaRepositories (
    basePackages = "com.system.billing.backend.client",
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig {

    @Primary 
    @Bean (name = "postgresDataSource")
    @ConfigurationProperties (prefix = "app.datasource.postgres")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Primary 
    @Bean (name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
        @Qualifier ("postgresDataSource") DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
            factory.setDataSource(dataSource);
            factory.setPackagesToScan("com.system.billing.backend.client");
            factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            return factory;
        }

    @Primary 
    @Bean (name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
        @Qualifier ("postgresEntityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
            return new JpaTransactionManager(factory.getObject());
        }
}