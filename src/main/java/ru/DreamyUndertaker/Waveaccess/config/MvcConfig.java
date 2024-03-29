package ru.DreamyUndertaker.Waveaccess.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/logout").setViewName("auth/login");
		registry.addViewController("/registration").setViewName("auth/registration");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/login").setViewName("auth/login");
		registry.addViewController("/admin").setViewName("admin");
	}
}
