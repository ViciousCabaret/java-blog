package wsb.studenci.blog.repository;

import org.springframework.data.repository.CrudRepository;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.Rate;
import wsb.studenci.blog.model.User;

import java.util.Optional;

public interface RateRepository extends CrudRepository<Rate, Integer>
{
    Optional<Rate> findByAuthorAndPost(User author, Post post);
}
