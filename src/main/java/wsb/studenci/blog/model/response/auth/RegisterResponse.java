package wsb.studenci.blog.model.response.auth;

public class RegisterResponse {
    private final String token;

    public RegisterResponse(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }
}
