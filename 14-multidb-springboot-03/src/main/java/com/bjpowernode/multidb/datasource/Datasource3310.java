package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:Datasource3310
 * Package:com.bjpowernode.multidb.datasource
 * Description:
 *
 * @Date:2018/10/17 15:03
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration("myDatasource3310")
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper.db3310", sqlSessionTemplateRef = "sqlSessionTemplate3310")
public class Datasource3310 {
    @Value("${spring.datasource.url3310}")
    private String url3310;
    @Value("${spring.datasource.username3310}")
    private String username3310;
    @Value("${spring.datasource.password3310}")
    private String password3310;

    /**
     *  配置数据库连接，阿里数据源druid连接池
     *     <bean id="dataSource3310" class="com.alibaba.druid.pool.DruidDataSource"
     *           init-method="init" destroy-method="close">
     *         <property name="url" value="jdbc:mysql://192.168.108.130:3310/test?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
     *         <property name="username" value="root"/>
     *         <property name="password" value="root"/>
     *     </bean>
     */
    @Bean
    public DruidDataSource dataSource3310(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3310);
        druidDataSource.setUsername(username3310);
        druidDataSource.setPassword(password3310);
        return druidDataSource;
    }

    /**
     * <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     *     <bean id="sqlSessionFactory3310" class="org.mybatis.spring.SqlSessionFactoryBean">
     *         <property name="dataSource" ref="dataSource3310"/>
     *     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory3310(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource3310());
        return sqlSessionFactoryBean;
    }

    /**
     * <!-- scan for mappers and let them be autowired -->
     *     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
     *         <property name="basePackage" value="com.bjpowernode.multi.mapper.db3310"/>
     *         <property name="sqlSessionFactory" ref="sqlSessionFactory3310"/>
     *     </bean>
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate3310() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory3310().getObject());
        return sqlSessionTemplate;
    }

}
