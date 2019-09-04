package com.pivotal.bookmarks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
@Configuration
public class BookmarksApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(BookmarksApplication.class, args);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS,"/").permitAll()//allow CORS option calls
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic();
	}
}
