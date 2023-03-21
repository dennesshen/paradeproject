package com.parade.paradeproject.slide.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            //取得http response headers
            Map<String, List<String>> responseHeaders = httpConnection.getHeaderFields();


            if (code == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                
                String line = null;
                while(( line = reader.readLine()) != null) {
                    message.append(line + "\n");
                }

                String html = changeHref(message, url);

                return ResponseEntity.ok().body(html);
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

    private String changeHref(StringBuffer message, String OriginUrl) {

        String domainName = OriginUrl.substring(0, findThirdSlash(OriginUrl));

        String regex = "href=\"([^\"]*)\"";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(message.toString());

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String href = matcher.group(1);
            if (href.startsWith("/")) {
                href = matcher.group().replace(href, domainName + href);
            }
            matcher.appendReplacement(result, href);
        }
        matcher.appendTail(result);

        return result.toString();

    }

    private int findThirdSlash(String originUrl) {

            int count = 0;
            int index = 0;

            while (count < 3) {
                index = originUrl.indexOf("/", index + 1);
                count++;
            }

            return index;

    }

}
