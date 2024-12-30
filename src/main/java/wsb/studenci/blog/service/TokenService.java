package wsb.studenci.blog.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

abstract public class TokenService
{
//    @Value("${auth.app.secret}")
//    private String secret;

    abstract protected String getSecret();

    public String create(Map<String, String> payload)
    {
        Algorithm algorithm = Algorithm.HMAC256(this.getSecret());
        String token = JWT.create()
            .withPayload(payload)
            .withIssuer("auth0")
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

        String payload = jwt.getPayload();
        JSONObject jsonObject = new JSONObject(payload);

        HashMap<String, String> map = new HashMap<>();
        map.put("userId", jsonObject.getString("userId"));

        return map;
    }
}
