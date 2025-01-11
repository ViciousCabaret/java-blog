package wsb.studenci.blog.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @Column(columnDefinition = "float default 0.0")
    private Float rate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<PostComment> postComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Rate> rates = new LinkedHashSet<>();

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
        this.rate = 0.0f;
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

    public Float getRate()
    {
        return rate;
    }

    public void setRate(Float rate)
    {
        this.rate = rate;
    }
}
