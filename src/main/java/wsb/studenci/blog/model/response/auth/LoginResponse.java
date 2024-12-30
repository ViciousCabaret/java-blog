package wsb.studenci.blog.model.response.auth;

public class LoginResponse
{
    private final String accessToken;
    private final String refreshToken;

    public LoginResponse(String accessToken, String refreshToken)
    {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }
}
