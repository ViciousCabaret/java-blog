package wsb.studenci.blog.model.request.postcomment;

public class EditPostCommentRequest
{
    private final String content;
    private final Integer postId;

    public EditPostCommentRequest(String content, Integer postId)
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
