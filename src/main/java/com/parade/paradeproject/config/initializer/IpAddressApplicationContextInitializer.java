package com.parade.paradeproject.config.initializer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(1)
public class IpAddressApplicationContextInitializer 
    implements ApplicationContextInitializer<ConfigurableApplicationContext>{

    
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        
        log.info("--------IpAddress initializer--------");
        
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        
        String serverAddress = environment.getProperty("server.address");
        String preferNetMask = environment.getProperty("setting.ip.prefer-net-mask");
        
        if (serverAddress!=null) return;
        
        if (preferNetMask == null || preferNetMask.isBlank()) {
            preferNetMask = ".*" ;
        }else {
            log.info("prefer-net-mask : " + preferNetMask);
            preferNetMask = preferNetMask.replaceAll("\\.", "\\\\.");
            preferNetMask = preferNetMask.replaceAll("\\*", "\\.*");

        }
        
        
        Map<String, Object> map = new HashMap<>();
        map.put("server.address", getLocalIpAddress(preferNetMask));
        
        map.entrySet().stream().forEach(m -> log.info(m.getKey()+":"+m.getValue()));
        
        MapPropertySource mapPropertySource = new MapPropertySource("ip_config", map);
        environment.getPropertySources().addLast(mapPropertySource);

        
    }
    
    private static String getLocalIpAddress(String preferIp) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface current = interfaces.nextElement();
                if (!current.isUp() || current.isLoopback() || current.isVirtual()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = current.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress currentAddr = addresses.nextElement();
                    if (currentAddr.isLoopbackAddress()) {
                        continue;
                    }
                    if (currentAddr.isSiteLocalAddress()) {
                        String ipAddress = currentAddr.getHostAddress();
                        
                        if(ipAddress.matches(preferIp)) {
                            return ipAddress;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            // Handle exception
        }
        return null;
    }
    
    

}
