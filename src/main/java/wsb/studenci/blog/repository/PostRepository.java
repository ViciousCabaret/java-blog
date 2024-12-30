package wsb.studenci.blog.repository;

import org.springframework.data.repository.CrudRepository;
import wsb.studenci.blog.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

}