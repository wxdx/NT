package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:Datasource3307
 * Package:com.bjpowernode.multidb.datasource
 * Description:
 *
 * @Date:2018/10/17 15:03
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration("myDatasource3307")
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper.db3307", sqlSessionTemplateRef = "sqlSessionTemplate3307")
public class Datasource3307 {
    @Value("${spring.datasource.url3307}")
    private String url3307;
    @Value("${spring.datasource.username3307}")
    private String username3307;
    @Value("${spring.datasource.password3307}")
    private String password3307;

    /**
     *  配置数据库连接，阿里数据源druid连接池
     *     <bean id="dataSource3307" class="com.alibaba.druid.pool.DruidDataSource"
     *           init-method="init" destroy-method="close">
     *         <property name="url" value="jdbc:mysql://192.168.108.130:3307/test?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
     *         <property name="username" value="root"/>
     *         <property name="password" value="root"/>
     *     </bean>
     */
    @Bean
    public DruidDataSource dataSource3307(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3307);
        druidDataSource.setUsername(username3307);
        druidDataSource.setPassword(password3307);
        return druidDataSource;
    }

    /**
     * <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     *     <bean id="sqlSessionFactory3307" class="org.mybatis.spring.SqlSessionFactoryBean">
     *         <property name="dataSource" ref="dataSource3307"/>
     *     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory3307(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource3307());
        return sqlSessionFactoryBean;
    }

    /**
     * <!-- scan for mappers and let them be autowired -->
     *     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
     *         <property name="basePackage" value="com.bjpowernode.multi.mapper.db3307"/>
     *         <property name="sqlSessionFactory" ref="sqlSessionFactory3307"/>
     *     </bean>
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate3307() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory3307().getObject());
        return sqlSessionTemplate;
    }

}
