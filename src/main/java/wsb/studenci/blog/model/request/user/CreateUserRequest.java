package wsb.studenci.blog.model.request.user;

public class CreateUserRequest {
    private String name;
    private String login;

    public CreateUserRequest(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
