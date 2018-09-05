package com.apigate.router.robirouter.services.api.token;

import com.apigate.router.robirouter.dto.GeneratedToken;
import com.apigate.router.robirouter.jpa.RobiRepository;
import com.apigate.router.robirouter.model.Profile;
import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {


   @Autowired
   private RobiRepository robiRepository;


   public void getTokenDetails(){


   }

   @Override
   public GeneratedToken generateAccessTokenByRefresh(String username){

      try {
         Profile profile = robiRepository.findByUsername(username);
         CloseableHttpClient httpClient = HttpClients.createDefault();
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();

         HttpPost httpPost = new HttpPost(profile.getRefreshTokenUrl());
         httpPost.setConfig(requestConfig);

         List<NameValuePair> params = new ArrayList<>();
         params.add(new BasicNameValuePair("grant_type", "refresh_token"));
         params.add(new BasicNameValuePair("refresh_token", profile.getRefreshToken()));
         params.add(new BasicNameValuePair("scope", profile.getWithScope()));
         httpPost.setEntity(new UrlEncodedFormEntity(params));
         String basicToken = Base64.getEncoder().encodeToString((profile.getConsumerKey() + ":" + profile.getConsumerSecret()).getBytes());
         httpPost.setHeader("Authorization", profile.getTokenType() + " " + basicToken);
         httpPost.setHeader("content-type", "application/x-www-form-urlencoded");

         CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
         int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

         if (statusCode != HttpStatus.SC_OK) {
            throw new IllegalStateException("Method failed: " + closeableHttpResponse.getStatusLine());
         }

         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(closeableHttpResponse.getEntity().getContent()));
         StringBuffer buf = new StringBuffer();
         String output;
         while ((output = bufferedReader.readLine()) != null) {
            buf.append(output);
         }

         Gson gson = new Gson();
         GeneratedToken generatedToken = gson.fromJson(buf.toString(), GeneratedToken.class);

         System.out.print(generatedToken);
         return generatedToken;
      } catch (Exception e) {
         return null;
      }

   }

   @Override
   public GeneratedToken generateAccessTokenByPassword(String username) {
      try {
         Profile profile = robiRepository.findByUsername(username);
         CloseableHttpClient httpClient = HttpClients.createDefault();
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();

         HttpPost httpPost = new HttpPost(profile.getRefreshTokenUrl());
         httpPost.setConfig(requestConfig);

         List<NameValuePair> params = new ArrayList<>();
         params.add(new BasicNameValuePair("grant_type", "password"));
         params.add(new BasicNameValuePair("username", profile.getUsername()));
         params.add(new BasicNameValuePair("password", profile.getPassword()));
         params.add(new BasicNameValuePair("scope", profile.getWithScope()));
         httpPost.setEntity(new UrlEncodedFormEntity(params));
         String basicToken = Base64.getEncoder().encodeToString((profile.getConsumerKey() + ":" + profile.getConsumerSecret()).getBytes());
         httpPost.setHeader("Authorization", profile.getTokenType() + " " + basicToken);
         httpPost.setHeader("content-type", "application/x-www-form-urlencoded");

         CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
         int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

         if (statusCode != HttpStatus.SC_OK) {
            throw new IllegalStateException("Method failed: " + closeableHttpResponse.getStatusLine());
         }

         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(closeableHttpResponse.getEntity().getContent()));
         StringBuffer buf = new StringBuffer();
         String output;
         while ((output = bufferedReader.readLine()) != null) {
            buf.append(output);
         }

         Gson gson = new Gson();
         GeneratedToken generatedToken = gson.fromJson(buf.toString(), GeneratedToken.class);
         profile.setAccessToken(generatedToken.getAccess_token());
         profile.setRefreshToken(generatedToken.getRefresh_token());
         profile.setTokenType(generatedToken.getToken_type());
         updateToken(profile);
         System.out.print(generatedToken);
         return generatedToken;
      } catch (Exception e) {
         return null;
      }

   }

   @Override
   public Profile updateToken(Profile profile) {
      return robiRepository.saveAndFlush(profile);
   }

   @Override
   public Profile findByUsername(String username) {
      return null;
   }

   @Override
   public Profile create(Profile profile) {
      return robiRepository.save(profile);
   }

}
