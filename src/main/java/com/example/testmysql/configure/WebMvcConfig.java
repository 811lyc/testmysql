package com.example.testmysql.configure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author LYC
 * @desc
 * @create 2020-08-04 14:50
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
	private final MyInterceptor myInterceptor;

	/**
	 * 添加拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("拦截器已设置");
		registry.addInterceptor(myInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(Arrays.asList("/auth/**"));
	}

	/**
	 * 允许跨域
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		log.info("拦截器已跨越");
		registry.addMapping("/**")
				.allowedMethods("*")
				.allowedOrigins("*")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);
	}
}
