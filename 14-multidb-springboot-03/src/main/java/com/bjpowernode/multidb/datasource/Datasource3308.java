package com.bjpowernode.multidb.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:Datasource3308
 * Package:com.bjpowernode.multidb.datasource
 * Description:
 *
 * @Date:2018/10/17 15:03
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration("myDatasource3308")
@MapperScan(basePackages = "com.bjpowernode.multidb.mapper.db3308", sqlSessionTemplateRef = "sqlSessionTemplate3308")
public class Datasource3308 {
    @Value("${spring.datasource.url3308}")
    private String url3308;
    @Value("${spring.datasource.username3308}")
    private String username3308;
    @Value("${spring.datasource.password3308}")
    private String password3308;

    /**
     *  配置数据库连接，阿里数据源druid连接池
     *     <bean id="dataSource3308" class="com.alibaba.druid.pool.DruidDataSource"
     *           init-method="init" destroy-method="close">
     *         <property name="url" value="jdbc:mysql://192.168.108.130:3308/test?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
     *         <property name="username" value="root"/>
     *         <property name="password" value="root"/>
     *     </bean>
     */
    @Bean
    public DruidDataSource dataSource3308(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url3308);
        druidDataSource.setUsername(username3308);
        druidDataSource.setPassword(password3308);
        return druidDataSource;
    }

    /**
     * <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     *     <bean id="sqlSessionFactory3308" class="org.mybatis.spring.SqlSessionFactoryBean">
     *         <property name="dataSource" ref="dataSource3308"/>
     *     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory3308(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource3308());
        return sqlSessionFactoryBean;
    }

    /**
     * <!-- scan for mappers and let them be autowired -->
     *     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
     *         <property name="basePackage" value="com.bjpowernode.multi.mapper.db3308"/>
     *         <property name="sqlSessionFactory" ref="sqlSessionFactory3308"/>
     *     </bean>
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate3308() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory3308().getObject());
        return sqlSessionTemplate;
    }

}
