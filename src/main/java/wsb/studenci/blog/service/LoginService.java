package wsb.studenci.blog.service;

import org.springframework.stereotype.Service;
import wsb.studenci.blog.exception.auth.InvalidCredentialsException;
import wsb.studenci.blog.model.User;
import wsb.studenci.blog.repository.UserRepository;
import wsb.studenci.blog.util.PBKDF2PasswordHasher;

@Service
public class LoginService
{
    private final PBKDF2PasswordHasher passwordHasher;
    private final UserRepository userRepository;

    public LoginService(
        PBKDF2PasswordHasher passwordHasher,
        UserRepository userRepository
    ) {
        this.passwordHasher = passwordHasher;
        this.userRepository = userRepository;
    }

    public User login(String login, String password)
    {
        User user = this.userRepository.findByLogin(login);
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        byte[] salt = user.salt();
        String hashedPassword = passwordHasher.hashPassword(password, salt);

        if (this.userRepository.findByLoginAndPassword(login, hashedPassword) == null) {
            throw new InvalidCredentialsException();
        }

        return user;
    }
}
