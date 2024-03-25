package com.trakingcontainer.security;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GPS_MAROC = "GPS MAROC";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String ACCESS_DENIED_MESSAGE_OPERATION = "You do not have permission to access this resource";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {
            "/api/account/login/**",
            "/api/account/changePassword/**",
            "/api/account/resetPassword/**",
            "/api/getDataFromConsole/**",
            "/api/sendImageFromConsole/**",
            "/api/grappe/create/**",

    };
    // public static final String[] PUBLIC_URLS = { "**" };
}
