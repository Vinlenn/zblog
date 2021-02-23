package com.vinlen.blog.common;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    @Value("${NutDao.url}")
	private String url;
	@Value("${NutDao.userName}")
	private String userName;
	@Value("${NutDao.password}")
	private String password;

	@Bean
	public Dao dao(){
		SimpleDataSource dataSource = new SimpleDataSource();
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return new NutDao(dataSource);
	}
}
