package wsb.studenci.blog.service.annotation.authentication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import wsb.studenci.blog.service.AuthenticationService;

@Aspect
@Component
public class RequireAuthenticationAspect
{
    private final AuthenticationService authenticationService;

    public RequireAuthenticationAspect(
        AuthenticationService authenticationService
    ) {
        this.authenticationService = authenticationService;
    }

    @Around("@annotation(RequireAuthentication)")
    public void requireAuthentication(ProceedingJoinPoint proceedingJoinPoint) throws RuntimeException
    {
        this.authenticationService.authenticate();
    }
}
