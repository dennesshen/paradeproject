package com.parade.paradeproject.config.initializer;


import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(2)
public class EurekaIpAddressApplicationContextInitializer 
    implements ApplicationContextInitializer<ConfigurableApplicationContext>{

    
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        
        log.info("--------EurekaIpAddress initializer--------");

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        
        String serverIpAddress = environment.getProperty("server.address");
                       

        if (serverIpAddress != null && !serverIpAddress.isBlank()) {
            
            Map<String, Object> map = new HashMap<>();
            map.put("eureka.instance.ip-address", serverIpAddress);
            map.entrySet().stream().forEach(m -> log.info(m.getKey()+":"+m.getValue()));
            MapPropertySource mapPropertySource = new MapPropertySource("eurekaIp_config", map);
            environment.getPropertySources().addLast(mapPropertySource);
        
        }
        
    }

    

}
