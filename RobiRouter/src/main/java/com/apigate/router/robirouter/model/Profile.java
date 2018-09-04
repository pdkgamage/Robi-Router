package com.apigate.router.robirouter.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Profile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name")
    public String name;
    @Column(name = "username")
    public String username;
    @Column(name = "password")
    public String password;
    @Column(name = "consumerKey")
    public String consumerKey;
    @Column(name = "consumerSecret")
    public String consumerSecret;
    @Column(name = "scope")
    public String scope;
    @Column(name = "tokenType")
    public String tokenType;
    @Column(name = "expiresIn")
    public int expiresIn;
    @Column(name = "refreshToken")
    public String refreshToken;
    @Column(name = "accessToken")
    public String accessToken;
    @Column(name = "generateTokenUrl")
    public String generateTokenUrl;
    @Column(name = "refreshTokenUrl")
    public String refreshTokenUrl;
    @Column(name = "withScope")
    public String withScope;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tokenCreatedAt")
    public Date tokenCreatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getGenerateTokenUrl() {
        return generateTokenUrl;
    }

    public void setGenerateTokenUrl(String generateTokenUrl) {
        this.generateTokenUrl = generateTokenUrl;
    }

    public String getRefreshTokenUrl() {
        return refreshTokenUrl;
    }

    public void setRefreshTokenUrl(String refreshTokenUrl) {
        this.refreshTokenUrl = refreshTokenUrl;
    }

    public String getWithScope() {
        return withScope;
    }

    public void setWithScope(String withScope) {
        this.withScope = withScope;
    }

    public Date getTokenCreatedAt() {
        return tokenCreatedAt;
    }

    public void setTokenCreatedAt(Date tokenCreatedAt) {
        this.tokenCreatedAt = tokenCreatedAt;
    }




}
