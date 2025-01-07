package wsb.studenci.blog.model.request.rate;

public class DeleteRatePostModel
{
    private final Integer postId;

    public DeleteRatePostModel(Integer postId)
    {
        this.postId = postId;
    }

    public Integer getPostId()
    {
        return postId;
    }
}
