package wsb.studenci.blog.model.request.rate;

public class CreateRatePostModel
{
    private final Integer postId;
    private final Integer score;

    public CreateRatePostModel(Integer postId, Integer score)
    {
        this.postId = postId;
        this.score = score;
    }

    public Integer getPostId()
    {
        return postId;
    }

    public Integer getScore()
    {
        return score;
    }
}
