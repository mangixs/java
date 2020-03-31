package com.wx.demo.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("yb_wangji_token")
public class YbWangjiToken {

    @Field("token_type ")
    private String tokenType;

    @Field("expires_in")
    private String expiresIn;

    @Field("refresh_token")
    private String refreshToken;

    @Field("access_token")
    private String accessToken;

    @Field("client_id")
    private String clientTd;

    @Field("client_secret")
    private String clientSecret;

    @Field("grant_type")
    private String grantType;

    @Field("scope")
    private String scope;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("type")
    private String type;

    @Field("modify_time")
    private String modifyTime;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
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

    public String getClientTd() {
        return clientTd;
    }

    public void setClientTd(String clientTd) {
        this.clientTd = clientTd;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "YbWangjiToken{" +
                "tokenType='" + tokenType + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", clientTd='" + clientTd + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", grantType='" + grantType + '\'' +
                ", scope='" + scope + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                '}';
    }
}

