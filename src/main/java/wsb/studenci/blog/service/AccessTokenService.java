package wsb.studenci.blog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wsb.studenci.blog.model.User;

import java.util.Map;

@Service
public class AccessTokenService extends TokenService
{
    @Value("${auth.app.secret}")
    private String secret;

    private final String tokenType = "Access";

    @Override
    protected String getSecret() {
        return this.secret;
    }

    public String create(Map<String, String> payload)
    {
        payload.put("type", "access");

        return super.create(payload);
    }

    public String create(User user, Map<String, String> payload)
    {
        payload.put("type", "access");
        payload.put("userId", user.getId().toString());

        return super.create(payload);
    }
}
