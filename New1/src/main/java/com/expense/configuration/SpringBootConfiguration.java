package com.expense.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.expense.*"})
public class SpringBootConfiguration {

    @Value("${spring.main.datasource.driver-class-name}")
    String driverClassName;

    @Value("${spring.main.datasource.url}")
    String mainUrl;

    @Value("${spring.main.datasource.username}")
    String mainUsername;

    @Value("${spring.main.datasource.password}")
    String mainPassword;

    @Bean(name = "mainDataSource")
    @Primary
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(mainUrl);
        hikariConfig.setUsername(mainUsername);
        hikariConfig.setPassword(mainPassword);

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("stock-service-pool");

        hikariConfig.setConnectionTimeout(60000);
        hikariConfig.setMinimumIdle(5);

        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "mainTransactionManager")
    public HibernateTransactionManager getTransactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Primary
    public DataSourceInitializer dataSourceInitializer(@Qualifier("mainDataSource") DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        return initializer;
    }

    @Bean(name = "sessionFactory")
    @Primary
    public SessionFactory getSessionFactory(@Qualifier("mainDataSource") DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("com.expense.*");
        return sessionBuilder.buildSessionFactory();
    }
}
