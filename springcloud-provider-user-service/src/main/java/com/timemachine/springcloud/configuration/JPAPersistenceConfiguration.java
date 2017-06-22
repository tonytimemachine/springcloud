package com.timemachine.springcloud.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JPA Persistence Configuration
 *
 * @author Liuguanglei tonytimemachine@gmail.com
 * @create 2017-06-上午11:26
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass = true) //启用JPA的事务管理
@EnableJpaRepositories(basePackages = "com.timemachine.springcloud.repository" )//启用JPA资源库并指定资源库接口位置
@EntityScan(basePackages = "com.timemachine.springcloud.entity")//指定实体的位置
public class JPAPersistenceConfiguration {



    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JPAPersistenceConfiguration.class);


    /*******************数据库和连接池配置信息,读取application.properties文件的属性值****************************/
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private long maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;


    @Value("${spring.datasource.filters}")
    private String filters;


    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;


    @Bean(name = "druidDataSource",initMethod = "init",destroyMethod = "close")
    public DataSource dataSource(){
        DruidDataSource druidDataSource =new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(url);

        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setConnectionProperties(connectionProperties);
        try {
            druidDataSource.setFilters(filters);
        } catch (SQLException e) {
            LOGGER.error("build datasoure exception ",e.getMessage());
        }

        return druidDataSource;
    }



    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource druidDataSource){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(druidDataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.timemachine.springcloud.entity");
        localContainerEntityManagerFactoryBean.setJpaProperties(buildHibernateProperties());
        localContainerEntityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter(){
            {
                setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
                setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
            }
        });
        return localContainerEntityManagerFactoryBean;
    }


    @Bean
    public PlatformTransactionManager transactionManager(DataSource druidDataSource, EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
        jpaTransactionManager.setDataSource(druidDataSource);
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }



    protected Properties buildHibernateProperties(){
        Properties hibernateProperties =new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        hibernateProperties.setProperty("hibernate.show_sql", "false");
        hibernateProperties.setProperty("hibernate.use_sql_comments", "false");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.generate_statistics", "false");
        hibernateProperties.setProperty("javax.persistence.validation.mode", "none");
        //Audit History flags
        hibernateProperties.setProperty("org.hibernate.envers.store_data_at_delete", "true");
        hibernateProperties.setProperty("org.hibernate.envers.global_with_modified_flag", "true");
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");
        hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true");


        return hibernateProperties;
    }

}
