package com.apigate.router.robirouter.services.api.token;

import com.apigate.router.robirouter.dto.GeneratedToken;
import com.apigate.router.robirouter.jpa.RobiRepository;
import com.apigate.router.robirouter.model.Profile;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;


@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {


   @Autowired
   private RobiRepository robiRepository;


   public void getTokenDetails(){


   }

   @Override
   public GeneratedToken generateAccessTokenByRefresh(String username) {
      Profile profile = robiRepository.findByUsername(username);

        try
         {

            /*HttpClientBuilder cb = HttpClientBuilder.create();
            SSLContextBuilder sslcb = new SSLContextBuilder();
            sslcb.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustSelfSignedStrategy());
            cb.setSslcontext(sslcb.build());
            CloseableHttpClient httpclient = cb.build();*/

            CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();

            HttpPost httpPost = new HttpPost(profile.getRefreshTokenUrl());
            httpPost.setConfig(requestConfig);
            HttpClientContext context = HttpClientContext.create();
            context.setAttribute("http.protocol.version", HttpVersion.HTTP_1_1);
            context.setAttribute("grant_type","password");
            context.setAttribute("username",profile.getUsername());
            context.setAttribute("password",profile.getPassword());
            context.setAttribute("scope",profile.getWithScope());
            String basicToken = Base64.getEncoder().encodeToString((profile.getConsumerKey()+":"+profile.getConsumerSecret()).getBytes());
            context.setAttribute("Authorization","Bearer "+basicToken);
            context.setAttribute("content-type","application/x-www-form-urlencoded");

            

            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost, context);

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

            String content = buf.toString();


         }
         catch(Exception ex)
         {
            ex.printStackTrace();
         }


      return null;
   }

   @Override
   public GeneratedToken generateAccessTokenByPassword() {
      return null;
   }

   @Override
   public Profile updateToken(GeneratedToken generatedToken) {
      return null;
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
