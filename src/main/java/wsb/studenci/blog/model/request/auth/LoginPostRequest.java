package wsb.studenci.blog.model.request.auth;

public class LoginPostRequest
{
    private String login;
    private String password;

    public LoginPostRequest(
            String login,
            String password
    ) {
        this.login = login;
        this.password = password;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }
}
