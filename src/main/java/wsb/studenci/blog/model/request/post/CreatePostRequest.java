package wsb.studenci.blog.model.request.post;

public class CreatePostRequest {
    private final String title;
    private final String content;
    private final Boolean active;

    public CreatePostRequest(String title, String content, Boolean active)
    {
        this.title = title;
        this.content = content;
        this.active = active;
    }

    public String title()
    {
        return title;
    }

    public String content()
    {
        return content;
    }

    public Boolean active()
    {
        return active;
    }
}
