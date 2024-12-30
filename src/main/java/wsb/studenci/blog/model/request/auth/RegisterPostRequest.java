package wsb.studenci.blog.model.request.auth;

public class RegisterPostRequest
{
    private final String login;
    private final String password;

    public RegisterPostRequest(String login, String password)
    {
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
