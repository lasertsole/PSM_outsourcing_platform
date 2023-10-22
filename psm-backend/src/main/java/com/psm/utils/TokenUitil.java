package com.psm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class TokenUitil {
    static private final String ISSUER = YmlUtil.getValue("Token.issuer");
    static private final String USER_ID = YmlUtil.getValue("Token.userID");
    static private final long MILLI_SECONDS_IN_HOUR = 1 * 60 * 60 * 1000;
    static private final Algorithm algorithm = Algorithm.HMAC256(YmlUtil.getValue("Token.secret"));

    public static String signToken(Integer userID, int expirationInHour){//创建cookie方式的令牌
        System.out.println(USER_ID);
        String token = JWT.create()
                        .withIssuer(ISSUER)
                        .withClaim(USER_ID, userID)
                        .withExpiresAt(new Date(System.currentTimeMillis() + expirationInHour * MILLI_SECONDS_IN_HOUR))
                        .sign(algorithm);
        return token;
    };

    public static Integer verifyToken(String token){//校验令牌
        JWTVerifier jwtVerifier =JWT.require(algorithm)
                                    .withIssuer(ISSUER)
                                    .build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        Integer userID = jwt.getClaim(USER_ID).asInt();
        return userID;
    }
}
