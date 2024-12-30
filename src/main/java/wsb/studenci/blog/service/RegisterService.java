package wsb.studenci.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsb.studenci.blog.exception.UserAlreadyExistsException;
import wsb.studenci.blog.model.User;
import wsb.studenci.blog.repository.UserRepository;
import wsb.studenci.blog.util.PBKDF2PasswordHasher;

@Service
public class RegisterService
{
    private final PBKDF2PasswordHasher passwordHasher;
    private final UserRepository userRepository;

    public RegisterService(
        PBKDF2PasswordHasher passwordHasher,
        UserRepository userRepository
    ) {
        this.passwordHasher = passwordHasher;
        this.userRepository = userRepository;
    }

    public User register(String login, String password)
    {
        if (this.userRepository.findByLogin(login) != null) {
            throw new UserAlreadyExistsException();
        }

        byte[] salt = passwordHasher.generateSalt();
        String hashedPassword = passwordHasher.hashPassword(password, salt);

        User user = new User(
            login,
            salt,
            hashedPassword
        );

        this.userRepository.save(user);

        return user;
    }
}
