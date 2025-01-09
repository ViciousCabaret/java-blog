package wsb.studenci.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
public class PostComment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer Id;

    @ManyToOne
    public User author;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public Post post;

    public String content;

    public PostComment(User author, Post post, String content)
    {
        this.author = author;
        this.post = post;
        this.content = content;
    }

    public PostComment()
    {
    }

    public Integer getId()
    {
        return Id;
    }

    public User getAuthor()
    {
        return author;
    }

    public Post getPost()
    {
        return post;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
