package com.yancy.boot.config;



import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;


import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Properties;

//数据源配置
@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisConfiguration {
    /**
     * account数据库配置前缀.
     */
    final static String ACCOUNT_PREFIX = "spring.datasource.druid.account.";
    /**
     * redpacket数据库配置前缀.
     */
    final static String REDPACKET_PREFIX = "spring.datasource.druid.redpacket.";
    /**
     * user数据库配置前缀
     */
    final static String USER_PREFIX = "spring.datasource.druid.user.";
    /**
     * 配置Account数据库的数据源
     *
     * @return the data source
     */
//    @Bean(name = "AccountDataSource")
//    @ConfigurationProperties(prefix = ACCOUNT_PREFIX)  // application.properties中对应属性的前缀
//    public DataSource accountDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }

    /**
     * 配置RedPacket数据库的数据源
     *
     * @return the data source
     */
//    @Bean(name = "RedPacketDataSource")
//    @ConfigurationProperties(prefix = REDPACKET_PREFIX)  // application.properties中对应属性的前缀
//    public DataSource redPacketDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }



    @Bean(name = "AccountDataSource")
    @Primary
    @Autowired
    public DataSource accountDataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build(env, ACCOUNT_PREFIX);
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("aDB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;

    }

    @Autowired
    @Bean(name = "RedPacketDataSource")
    public AtomikosDataSourceBean redPacketDataSource(Environment env) {

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build(env, REDPACKET_PREFIX);
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("rDB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);

        return ds;
    }

    @Bean(name = "UserDataSource")
    @Autowired
    public DataSource userDataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build(env, USER_PREFIX);
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("uDB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;

    }


    private Properties build(Environment env, String prefix) {

        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        prop.put("initialSize", env.getProperty(prefix + "initial-size", Integer.class));
        prop.put("maxActive", env.getProperty(prefix + "max-active", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "min-idle", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "max-active", Integer.class));
//        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));

//        prop.put("maxPoolPreparedStatementPerConnectionSize",
//                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));

//        prop.put("maxPoolPreparedStatementPerConnectionSize",
//                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validation-query"));
//        prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
        prop.put("testOnBorrow", env.getProperty(prefix + "test-on-borrow", Boolean.class));
//        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(prefix + "test-while-idle", Boolean.class));
        prop.put("timeBetweenEvictionRunsMillis",
                env.getProperty(prefix + "time-between-eviction-runs-millis", Integer.class));
//        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
//        prop.put("filters", env.getProperty(prefix + "filters"));

        return prop;
    }

    /**
     * 注入事物管理器
     * @return
     */
    @Bean(name = "xatx")
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //控制台管理用户，加入下面2行 进入druid后台就需要登录
        //servletRegistrationBean.addInitParameter("loginUsername", "admin");
        //servletRegistrationBean.addInitParameter("loginPassword", "admin");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public StatFilter statFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true); //slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
        statFilter.setMergeSql(true); //SQL合并配置
        statFilter.setSlowSqlMillis(1000);//slowSqlMillis的缺省值为3000，也就是3秒。
        return statFilter;
    }

    @Bean
    public WallFilter wallFilter(){
        WallFilter wallFilter = new WallFilter();
        //允许执行多条SQL
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);
        return wallFilter;
    }
}
