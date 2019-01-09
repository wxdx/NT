package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:Datasource3309
 * Package:com.bjpowernode.multidb.datasource
 * Description:
 *
 * @Date:2018/10/17 15:03
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration("myDatasource3309")
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper.db3309", sqlSessionTemplateRef = "sqlSessionTemplate3309")
public class Datasource3309 {
    @Value("${spring.datasource.url3309}")
    private String url3309;
    @Value("${spring.datasource.username3309}")
    private String username3309;
    @Value("${spring.datasource.password3309}")
    private String password3309;

    /**
     *  配置数据库连接，阿里数据源druid连接池
     *     <bean id="dataSource3309" class="com.alibaba.druid.pool.DruidDataSource"
     *           init-method="init" destroy-method="close">
     *         <property name="url" value="jdbc:mysql://192.168.108.130:3309/test?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
     *         <property name="username" value="root"/>
     *         <property name="password" value="root"/>
     *     </bean>
     */
    @Bean
    public DruidDataSource dataSource3309(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3309);
        druidDataSource.setUsername(username3309);
        druidDataSource.setPassword(password3309);
        return druidDataSource;
    }

    /**
     * <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     *     <bean id="sqlSessionFactory3309" class="org.mybatis.spring.SqlSessionFactoryBean">
     *         <property name="dataSource" ref="dataSource3309"/>
     *     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory3309(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource3309());
        return sqlSessionFactoryBean;
    }

    /**
     * <!-- scan for mappers and let them be autowired -->
     *     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
     *         <property name="basePackage" value="com.bjpowernode.multi.mapper.db3309"/>
     *         <property name="sqlSessionFactory" ref="sqlSessionFactory3309"/>
     *     </bean>
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate3309() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory3309().getObject());
        return sqlSessionTemplate;
    }

}
