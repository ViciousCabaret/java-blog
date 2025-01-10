package wsb.studenci.blog.model;

import jakarta.persistence.*;

@Entity
public class Rate
{
    public static final Integer MIN_RATE = 1;
    public static final Integer MAX_RATE = 5;

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
        if (score < MIN_RATE || score > MAX_RATE) {
            throw new IllegalArgumentException("score must be between 1 and 5");
        }

        this.score = score;
    }
}
