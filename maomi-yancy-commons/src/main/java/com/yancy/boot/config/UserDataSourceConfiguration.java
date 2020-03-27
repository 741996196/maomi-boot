package com.yancy.boot.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.yancy.boot.mapper.user.mapper"}, sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfiguration {

    /**
     * mybatis的xml文件.
     */
    public static final String MAPPER_XML_LOCATION = "classpath*:com/yancy/boot/mapper/user/mapper/*.xml";

    @Autowired
    @Qualifier("UserDataSource")
    DataSource userDataSource;

    /**
     * 配置Sql Session模板
     */
    @Bean
    public SqlSessionTemplate springSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(userSqlSessionFactory());
    }

    /**
     * 配置SQL Session工厂
     */
    @Bean
    public SqlSessionFactory userSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(userDataSource);
        //指定XML文件路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATION));
        return factoryBean.getObject();
    }
}
