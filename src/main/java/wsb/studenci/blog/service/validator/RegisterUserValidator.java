package wsb.studenci.blog.service.validator;

import wsb.studenci.blog.repository.UserRepository;

public class RegisterUserValidator
{
    public final UserRepository userRepository;

    public RegisterUserValidator(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}
