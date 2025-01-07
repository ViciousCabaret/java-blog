package wsb.studenci.blog.exception.rate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Rate not found")
public class RateNotFoundException extends RuntimeException
{
}
