package com.bjpowernode.multi.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:DataSource
 * Package:com.bjpowernode.multi.datasource
 * Description:
 *
 * @Date:2018/10/17 16:36
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration("myDatasource")
@MapperScan(basePackages = "com.bjpowernode.multi.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSource {
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
     * <!--动态数据源-->
     *     <bean id="dynamicDataSource" class="com.bjpowernode.multi.datasource.DynamicDataSource">
     *         <property name="targetDataSources">
     *             <map>
     *                 <entry key="db3307" value-ref="dataSource3307"/>
     *                 <entry key="db3308" value-ref="dataSource3308"/>
     *                 <entry key="db3309" value-ref="dataSource3309"/>
     *                 <entry key="db3310" value-ref="dataSource3310"/>
     *             </map>
     *         </property>
     *     </bean>
     */

    @Bean
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put("db3307", dataSource3307());
        datasourceMap.put("db3308", dataSource3308());
        datasourceMap.put("db3309", dataSource3309());
        datasourceMap.put("db3310", dataSource3310());
        dynamicDataSource.setTargetDataSources(datasourceMap);
        return dynamicDataSource;
    }

    /**
     * <!-- MyBatis sqlSessionFactory 配置 mybatis -->
     *     <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
     *         <property name="dataSource" ref="dynamicDataSource"/>
     *     </bean>
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }

    /**
     *  <!-- scan for mappers and let them be autowired -->
     *     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
     *         <property name="basePackage" value="com.bjpowernode.multi.mapper"/>
     *         <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
     *     </bean>
     */
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sqlSessionTemplate;
    }




}
