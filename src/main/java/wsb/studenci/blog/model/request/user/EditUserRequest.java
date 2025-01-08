package wsb.studenci.blog.model.request.user;

public class EditUserRequest
{
    private final String name;
    private final String surname;
    private final String bio;

    public EditUserRequest(String name, String surname, String bio)
    {
        this.name = name;
        this.surname = surname;
        this.bio = bio;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getBio()
    {
        return bio;
    }
}
