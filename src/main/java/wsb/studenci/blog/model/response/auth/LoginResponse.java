package wsb.studenci.blog.model.response.auth;

import wsb.studenci.blog.model.User;

public class LoginResponse
{
    private final String accessToken;
    private final User user;

    public LoginResponse(String accessToken, User user)
    {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public User getUser()
    {
        return user;
    }
}
