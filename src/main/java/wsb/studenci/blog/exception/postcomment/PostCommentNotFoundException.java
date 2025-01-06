package wsb.studenci.blog.exception.postcomment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Post comment not found")
public class PostCommentNotFoundException extends RuntimeException
{
}
