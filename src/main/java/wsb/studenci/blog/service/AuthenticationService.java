package wsb.studenci.blog.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import wsb.studenci.blog.exception.auth.UnauthorizedException;
import wsb.studenci.blog.model.User;
import wsb.studenci.blog.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AccessTokenService tokenService;
    private final HttpServletRequest httpServletRequest;

    public AuthenticationService(
        UserRepository userRepository,
        AccessTokenService tokenService,
        HttpServletRequest httpServletRequest
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.httpServletRequest = httpServletRequest;
    }

    public User authenticate() throws UnauthorizedException
    {
        try {
            String token = this.httpServletRequest.getHeader("Authorization");

            if (null == token) {
                throw new UnauthorizedException();
            }

            Map<String, String> map = this.tokenService.decode(token);
            Integer userId = Integer.valueOf(map.get("userId"));

            Optional<User> user = this.userRepository.findById(userId);

            if (user.isEmpty()) {
                throw new UnauthorizedException();
            }

            return user.get();
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }
}
