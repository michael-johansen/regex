package no.ciber.regex.persistence;

import org.h2.jdbcx.JdbcConnectionPool;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.io.IOException;

import static org.hibernate.cfg.AvailableSettings.*;

/**
 * This software is written by Michael on 21/10/2014.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EnableCaching
public class PersistenceConfiguration {
    @Bean
    @Autowired
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        emfBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emfBean.setPackagesToScan("no.ciber.regex.persistence.model");
        emfBean.getJpaPropertyMap().put(HBM2DDL_AUTO, "create");
        emfBean.getJpaPropertyMap().put(FORMAT_SQL, true);
        emfBean.getJpaPropertyMap().put(GENERATE_STATISTICS, true);
        emfBean.getJpaPropertyMap().put(DIALECT, H2Dialect.class.getName());
        emfBean.getJpaPropertyMap().put(USE_SECOND_LEVEL_CACHE, true);
        emfBean.getJpaPropertyMap().put(USE_QUERY_CACHE, true);
        emfBean.getJpaPropertyMap().put(CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");

        emfBean.setDataSource(dataSource);
        emfBean.afterPropertiesSet();

        return emfBean.getObject();
    }


    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public CacheManager cacheManager() throws IOException {
        ClassPathResource resource = new ClassPathResource("ehcache.xml");
        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create(resource.getInputStream());
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(cacheManager) {
            @Override
            public Cache getCache(String name) {
                Cache cache = super.getCache(name);
                if (cache == null) {
                    getCacheManager().addCache(name);
                    return super.getCache(name);
                }
                return cache;
            }
        };

        return ehCacheCacheManager;
    }

    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(
                JdbcConnectionPool.create("jdbc:h2:~/regex.db;AUTO_SERVER=TRUE", "sa", "sa")
        );
    }

}
