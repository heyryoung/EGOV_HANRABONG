package com.hanrabong.web.cfg;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@Configuration
@ComponentScan(basePackages= {"com.hanrabong.web"})
@Import({
	MybatisConfig.class, ServletConfig.class
})
public class RootConfig {
	@Bean
	public DataSource dataSource() {
		/*		HikariCoonfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/hanrabong?serverTimezone=UTC");
		hikariConfig.setUsername("hanrabong");
		hikariConfig.setPassword("hanrabong");
	
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
	*/	
	  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		    dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		    dataSource.setUrl("jdbc:mariadb://172.168.0.89/hanrabong");
		    dataSource.setUsername("hanrabong");
		    dataSource.setPassword("hanrabong");

		return dataSource;
	}	
	
	@Bean 
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	
}