package wsb.studenci.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wsb.studenci.blog.exception.post.PostNotFoundException;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.PostComment;
import wsb.studenci.blog.model.request.comment.CreatePostCommentRequest;
import wsb.studenci.blog.repository.PostCommentRepository;
import wsb.studenci.blog.repository.PostRepository;
import wsb.studenci.blog.service.AuthenticationService;
import wsb.studenci.blog.service.annotation.authentication.RequireAuthentication;

import java.util.Optional;

@Controller
@RequestMapping("/post/comment")
public class PostCommentController extends AbstractController
{
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    public PostCommentController(
        AuthenticationService authenticationService,
        PostCommentRepository postCommentRepository,
        PostRepository postRepository
    ) {
        super(authenticationService);
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
    }

    @RequireAuthentication
    @PostMapping
    @ResponseBody
    public ResponseEntity<PostComment> create(CreatePostCommentRequest request)
    {
        Optional<Post> post = this.postRepository.findById(request.getPostId());

        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }

        PostComment postComment = new PostComment(
            this.authenticationService.authenticate(),
            post.get(),
            request.getContent()
        );

        this.postCommentRepository.save(postComment);

        return ResponseEntity.ok(postComment);
    }

    public ResponseEntity
}
