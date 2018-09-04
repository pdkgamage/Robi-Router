package com.apigate.router.robirouter.services.api.token;

import com.apigate.router.robirouter.dto.GeneratedToken;
import com.apigate.router.robirouter.model.Profile;


public interface ProfileService {

    public void getTokenDetails();
    public GeneratedToken generateAccessTokenByRefresh(String username);
    public GeneratedToken generateAccessTokenByPassword();
    public Profile updateToken(GeneratedToken generatedToken);
    public Profile findByUsername(String username);
    public Profile create(Profile profile);


}
