package br.soc.avaliacao.config.client;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignSupportConfig {

    @Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
    
}