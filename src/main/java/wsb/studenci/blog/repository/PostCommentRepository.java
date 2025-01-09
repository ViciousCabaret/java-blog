package wsb.studenci.blog.repository;

import org.springframework.data.repository.CrudRepository;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.PostComment;

import java.util.List;

public interface PostCommentRepository extends CrudRepository<PostComment, Integer>
{
    List<PostComment> findAllByPost(Post post);
}
