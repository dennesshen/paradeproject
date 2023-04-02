package com.parade.paradeproject.slide.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.parade.paradeproject.config.htmlchange.HtmlRegexList;
import com.parade.paradeproject.util.exception.HttpForwardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.parade.paradeproject.slide.dto.DtoOfHttpRequest;


@Service
public class HttpConnectService {

    @Autowired
    private HtmlRegexList htmlRegexList;

    public ResponseEntity<HttpRecord> connect(DtoOfHttpRequest receiveData) {
        
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

                String html = changeHtml(message, url);
                String title = findMetaTitle(message);
                String image = findMetaImage(message);

                return ResponseEntity.ok().body(new HttpRecord(html, title, image));
            }
            
            throw new HttpForwardException("轉發失敗，錯誤代碼:" + code, code);
            
            
        } catch (HttpForwardException e){

            throw e;

        } catch (Exception e) {
            
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
            
        } finally {
            
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }
        
    }

    private String findMetaTitle(StringBuffer message) {
        String regex = "<meta property=\"og:title\" content=\"(.*)\"[^>]*>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message.toString());

        String title = "";
        if (matcher.find()){

            title = matcher.group(1);

        }

        return title;

    }

    private String findMetaImage(StringBuffer message) {

        String regex = "<meta property=\"og:image\" content=\"(.*)\"[^>]*>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message.toString());

        String image = "";
        if (matcher.find()){

            image = matcher.group(1);

        }

        return image;

    }

    private String changeHtml(StringBuffer message, String OriginUrl) {

        String domainName = findDomainName(OriginUrl);

        for (Entry<Long, String> regex : htmlRegexList.regexList().entrySet()) {
            message = addDomainInUrl(message, domainName, regex.getValue());
        }

        return message.toString();

    }

    private StringBuffer addDomainInUrl(StringBuffer message,
                                        String domainName,
                                        String regex) {

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(message.toString());

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String url = matcher.group(1);
            if (url.startsWith("/")) {
                url = matcher.group().replace(url, domainName + url);
            }
            if (url.startsWith("~")) {
                url = matcher.group().replace(url, domainName + url.substring(1));
            }
            matcher.appendReplacement(result, url);
        }
        matcher.appendTail(result);
        return result;
    }


    private String findDomainName(String originUrl) {

        String regex = "((?:http|https)://[^/]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(originUrl);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new HttpForwardException("轉發失敗，網址格式錯誤", 400);
    }


    public record HttpRecord(String html, String title, String image_url) {}

}
