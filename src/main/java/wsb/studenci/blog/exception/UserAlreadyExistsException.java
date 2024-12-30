package wsb.studenci.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already exists", code = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException
{
}
