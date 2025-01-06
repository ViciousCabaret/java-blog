package wsb.studenci.blog.model.request.postcomment;

public class CreatePostCommentRequest
{
    private final String content;
    private final Integer postId;

    public CreatePostCommentRequest(String content, Integer postId)
    {
        this.content = content;
        this.postId = postId;
    }

    public String getContent()
    {
        return content;
    }

    public Integer getPostId()
    {
        return postId;
    }
}
