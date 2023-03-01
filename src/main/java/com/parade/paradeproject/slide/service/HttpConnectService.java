package com.parade.paradeproject.slide.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.parade.paradeproject.slide.dto.DtoOfHttpRequest;

@Service
public class HttpConnectService {

    
    
    public ResponseEntity<String> connect(DtoOfHttpRequest receiveData) {
        
        String httpMethod = receiveData.getHttpMethod();
        Map<String, String> headers = receiveData.getHeaders();        
        String url = receiveData.getUrl();
        String body = receiveData.getBody();
        
        HttpURLConnection httpConnection = null;
        
        try {
            URL targetUrl = new URL(url);
            
            httpConnection = (HttpURLConnection)targetUrl.openConnection();
            
            httpConnection.setRequestMethod(httpMethod.toUpperCase());
            
            for (Entry<String, String> header : headers.entrySet()) {
                
                httpConnection.setRequestProperty(header.getKey(), header.getValue());
                
            }
            
            
            httpConnection.connect();
            
            if (!httpMethod.toUpperCase().equals("GET") && body != null) {
                OutputStream out = httpConnection.getOutputStream();
                out.write(body.getBytes());
                out.flush();
                out.close();
            }
            
            int code = httpConnection.getResponseCode();
            
            StringBuffer message = new StringBuffer();

            if (code == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                
                String line = null;
                while(( line = reader.readLine()) != null) {
                    message.append(line + "\n");
                }
                
                return ResponseEntity.ok().body(message.toString());
            }
            
            return ResponseEntity.status(code).build();
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
            
        } finally {
            
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        
        
    }
    
    
}
