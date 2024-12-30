package wsb.studenci.blog.model;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User author;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean active;

    public Post(
        User author,
        String title,
        String content,
        Boolean active
    ) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.active = active;
    }

    public Post()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public User getAuthor()
    {
        return author;
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }
}
