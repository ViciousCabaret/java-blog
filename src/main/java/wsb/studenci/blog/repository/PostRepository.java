package wsb.studenci.blog.repository;

import org.springframework.data.repository.CrudRepository;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.User;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer>
{
    List<Post> findAllByAuthor(User user);
}