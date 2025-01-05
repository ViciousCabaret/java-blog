package wsb.studenci.blog.service;

import org.springframework.stereotype.Service;
import wsb.studenci.blog.exception.auth.InvalidPasswordException;
import wsb.studenci.blog.model.User;
import wsb.studenci.blog.repository.UserRepository;
import wsb.studenci.blog.util.PBKDF2PasswordHasher;

@Service
public class PasswordChangeService
{
    private final UserRepository userRepository;
    private final PBKDF2PasswordHasher passwordHasher;

    public PasswordChangeService(
        UserRepository userRepository,
        PBKDF2PasswordHasher passwordHasher
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public void changePassword(User user, String oldPassword, String newPassword) throws InvalidPasswordException
    {
        String oldPasswordHashed = this.passwordHasher.hashPassword(user.password(), user.salt());
        if (!oldPasswordHashed.equals(oldPassword)) {
            throw new InvalidPasswordException();
        }

        String newPasswordHashed = this.passwordHasher.hashPassword(newPassword, user.salt());
        user.changePassword(newPasswordHashed);
        this.userRepository.save(user);
    }
}
