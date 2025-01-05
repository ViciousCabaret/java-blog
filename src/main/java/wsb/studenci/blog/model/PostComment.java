package wsb.studenci.blog.model;

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
}
