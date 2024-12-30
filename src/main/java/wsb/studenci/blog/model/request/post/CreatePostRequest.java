package wsb.studenci.blog.model.request.post;

public class CreatePostRequest {
    private String title;
    private String content;

    public CreatePostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
