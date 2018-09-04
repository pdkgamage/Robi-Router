package com.apigate.router.robirouter.services.api.token;

import com.apigate.router.robirouter.dto.GeneratedToken;
import com.apigate.router.robirouter.jpa.RobiRepository;
import com.apigate.router.robirouter.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {


   @Autowired
   private RobiRepository robiRepository;


   public void getTokenDetails(){


   }

   @Override
   public GeneratedToken generateAccessTokenByRefresh() {
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
