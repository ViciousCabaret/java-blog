package wsb.studenci.blog.model;

import jakarta.persistence.*;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String login;

    @Lob
    @Column(name = "salt", columnDefinition = "BLOB", nullable = false)
    private byte[] salt;

    @Column(name = "password", nullable = false)
    private String password;

    private String name;
    private String surname;
    private String bio;

    public User(String login, byte[] salt, String password)
    {
        this.login = login;
        this.salt = salt;
        this.password = password;
    }

    public User()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public String password()
    {
        return password;
    }

    public byte[] salt()
    {
        return salt;
    }

    public void changePassword(String password)
    {
        this.password = password;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }
}
