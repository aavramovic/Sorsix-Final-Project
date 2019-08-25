package com.sorsix.finkicommunity.security;

public class JwtProperties {
    public static final String SECRET = "finkicommunity2019";
    public static final int EXPIRATION_TIME = 60 * 60 * 1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
