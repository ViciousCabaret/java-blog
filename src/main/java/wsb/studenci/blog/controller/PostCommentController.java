package wsb.studenci.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsb.studenci.blog.exception.ForbiddenException;
import wsb.studenci.blog.exception.post.PostNotFoundException;
import wsb.studenci.blog.exception.postcomment.PostCommentNotFoundException;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.PostComment;
import wsb.studenci.blog.model.User;
import wsb.studenci.blog.model.request.postcomment.CreatePostCommentRequest;
import wsb.studenci.blog.model.request.postcomment.EditPostCommentRequest;
import wsb.studenci.blog.repository.PostCommentRepository;
import wsb.studenci.blog.repository.PostRepository;
import wsb.studenci.blog.service.AuthenticationService;
import wsb.studenci.blog.service.annotation.authentication.RequireAuthentication;

import java.util.Optional;

@Controller
@RequestMapping("/post-comments")
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
    public ResponseEntity<PostComment> create(@RequestBody CreatePostCommentRequest request)
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

    @RequireAuthentication
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PostComment> edit(@RequestBody EditPostCommentRequest request, @PathVariable Integer id)
    {
        Optional<Post> post = this.postRepository.findById(request.getPostId());

        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }

        Optional<PostComment> optionalPostComment = this.postCommentRepository.findById(id);
        if (optionalPostComment.isEmpty()) {
            throw new PostCommentNotFoundException();
        }

        PostComment postComment = optionalPostComment.get();
        postComment.setContent(request.getContent());

        this.postCommentRepository.save(postComment);

        return ResponseEntity.ok(postComment);
    }


    @RequireAuthentication
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        return postCommentRepository.findById(id)
            .map(postComment -> {
                User user = this.authenticationService.authenticate();
                if (!postComment.getAuthor().equals(user)) {
                    throw new ForbiddenException();
                }
                postCommentRepository.delete(postComment);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
