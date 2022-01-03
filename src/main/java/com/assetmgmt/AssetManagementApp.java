package com.assetmgmt;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EntityScan(basePackageClasses = { AssetManagementApp.class, Jsr310JpaConverters.class })
@Configuration
@EnableCaching
public class AssetManagementApp extends SpringBootServletInitializer {
	
	  public static void main(String[] args) {
	  SpringApplication.run(AssetManagementApp.class, args); }
	 
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AssetManagementApp.class);
    }
	
	@PostConstruct
	void init() {
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
