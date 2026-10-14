package com.system.billing.backend.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration 
@EnableJpaRepositories (
    basePackages = "com.system.billing.backend.invoice",
    entityManagerFactoryRef = "oracleEntityManagerFactory",
    transactionManagerRef = "oracleTransactionManager"
)
public class OracleConfig {

    @Bean (name = "oracleDataSource")
    @ConfigurationProperties (prefix = "app.datasource.oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean (name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(
        @Qualifier("oracleDataSource") DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
            factory.setDataSource(dataSource);
            factory.setPackagesToScan("com.system.billing.backend.invoice");
            factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            return factory;
        }

    @Bean (name = "oracleTransactionManager")
    public PlatformTransactionManager oracleTransactionManager(
        @Qualifier("oracleEntityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
            return new JpaTransactionManager(factory.getObject());
        }
}

