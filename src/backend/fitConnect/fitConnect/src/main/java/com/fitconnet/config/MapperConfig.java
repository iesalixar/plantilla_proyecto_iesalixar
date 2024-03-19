package com.fitconnet.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fitconnet.mappers.ActivityMapper;
import com.fitconnet.mappers.UserMapper;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = "com.fitconnet.mappers")
public class MapperConfig {

	@Bean
	public ActivityMapper activityMapper() {
		return Mappers.getMapper(ActivityMapper.class);
	}

	@Bean
	public UserMapper userMapper() {
		return Mappers.getMapper(UserMapper.class);
	}

}
