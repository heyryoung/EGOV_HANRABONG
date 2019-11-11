package com.hanrabong.web.cfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages= {"com.hanrabong.web"})
//@EnableAspectJAutoProxy
//@EnableTransactionManagement  // 개념적으로 root가 아닌 mybatis에 선언함. rootconfig에서 mybatis.config를 import하기에  문제 없을 듯.하였으나 문제가 있었다
public class MybatisConfig {
		@Autowired 
		ApplicationContext applicationContext;
	
		@Bean
		public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
		  SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		  factoryBean.setDataSource(dataSource);
		  factoryBean.setConfigLocation(applicationContext.getResource("classpath:META-INF/mybatis-config.xml"));
		  factoryBean.setMapperLocations(applicationContext.getResources("classpath:/com/hanrabong/web/**/*.xml"));
		  return factoryBean;
		}
		
		@Bean
		public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		  return new SqlSessionTemplate(sqlSessionFactory);
		}
		
		
}