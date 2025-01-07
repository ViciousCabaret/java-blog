package wsb.studenci.blog.model;

import jakarta.persistence.*;

@Entity
public class Rate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer Id;

    @ManyToOne
    public User author;

    @ManyToOne
    public Post post;

    public Integer score;

    public Rate(User author, Post post, Integer score)
    {
        this.author = author;
        this.post = post;
        this.score = score;
    }

    public Rate()
    {
    }

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer id)
    {
        Id = id;
    }

    public User getAuthor()
    {
        return author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public Post getPost()
    {
        return post;
    }

    public void setPost(Post post)
    {
        this.post = post;
    }

    public Integer getScore()
    {
        return score;
    }

    public void setScore(Integer score)
    {
        this.score = score;
    }
}
