package wsb.studenci.blog.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "invalid credentials")
public class InvalidCredentialsException extends RuntimeException
{
}
