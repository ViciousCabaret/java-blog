package wsb.studenci.blog.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Invalid password")
public class InvalidPasswordException extends RuntimeException
{
}
