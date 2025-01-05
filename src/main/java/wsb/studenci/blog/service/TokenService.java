package wsb.studenci.blog.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

abstract public class TokenService
{
    abstract protected String getSecret();
    abstract protected Date getExpirationDate();

    public String create(Map<String, String> payload)
    {
        Algorithm algorithm = Algorithm.HMAC256(this.getSecret());
        String token = JWT.create()
            .withPayload(payload)
            .withIssuer("auth0")
            .withExpiresAt(this.getExpirationDate())
            .sign(algorithm);

        System.out.println(token);
        return token;
    }

    public Map<String, String> decode(String token)
    {
        Algorithm algorithm = Algorithm.HMAC256(this.getSecret());
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("auth0")
            .build();

        DecodedJWT jwt = verifier.verify(token);

        String payload = new String(Base64.getDecoder().decode(jwt.getPayload()));
        JSONObject jsonObject = new JSONObject(payload);

        HashMap<String, String> map = new HashMap<>();
        map.put("userId", jsonObject.getString("userId"));

        return map;
    }
}
