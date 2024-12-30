package wsb.studenci.blog.repository;

import org.springframework.data.repository.CrudRepository;
import wsb.studenci.blog.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);
}