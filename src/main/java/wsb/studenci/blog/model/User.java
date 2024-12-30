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

    public User(
        String login,
        byte[] salt,
        String password
    )
    {
        this.login = login;
        this.salt = salt;
        this.password = password;
    }

    public User()
    {
    }

    public Integer id()
    {
        return id;
    }

    public String login()
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
}
