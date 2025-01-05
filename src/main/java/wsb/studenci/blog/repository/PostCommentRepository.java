package wsb.studenci.blog.repository;

import org.springframework.data.repository.CrudRepository;
import wsb.studenci.blog.model.PostComment;

public interface PostCommentRepository extends CrudRepository<PostComment, Integer>
{
}
