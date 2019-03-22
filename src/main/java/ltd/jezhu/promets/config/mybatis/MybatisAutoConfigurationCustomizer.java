package ltd.jezhu.promets.config.mybatis;

import ltd.jezhu.promets.system.dao.BaseDaoImpl;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

/**
 * mybatis自动配置自定义
 * @author ymzhu
 * @date 2019/3/22 15:35
 */
@Configuration
@MapperScan({"classpath:mapper/*.xml"})
@ConditionalOnClass({SqlSessionFactory.class, MybatisAutoConfiguration.class})
public class MybatisAutoConfigurationCustomizer extends MybatisAutoConfiguration {
    public MybatisAutoConfigurationCustomizer(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public BaseDaoImpl baseDao(SqlSessionTemplate sqlSessionTemplate) {
        BaseDaoImpl dao = new BaseDaoImpl();
        dao.setSqlSessionTemplate(sqlSessionTemplate);
        return dao;
    }
}
