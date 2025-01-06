package wsb.studenci.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsb.studenci.blog.exception.ForbiddenException;
import wsb.studenci.blog.exception.post.PostNotFoundException;
import wsb.studenci.blog.exception.user.UserNotFoundException;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.User;
import wsb.studenci.blog.model.request.post.CreatePostRequest;
import wsb.studenci.blog.model.request.post.UpdatePostRequest;
import wsb.studenci.blog.repository.PostRepository;
import wsb.studenci.blog.repository.UserRepository;
import wsb.studenci.blog.service.AuthenticationService;
import wsb.studenci.blog.service.annotation.authentication.RequireAuthentication;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Controller
@RequestMapping(path = "/posts")
public class PostController extends AbstractController
{
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(
        AuthenticationService authenticationService,
        PostRepository postRepository,
        UserRepository userRepository
    ) {
        super(authenticationService);
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @RequireAuthentication
    @PostMapping
    @ResponseBody
    public ResponseEntity<Post> create(@RequestBody CreatePostRequest request)
    {
        User user = this.authenticationService.authenticate();
        Post post = new Post(
            user,
            request.title(),
            request.content(),
            request.active()
        );

        postRepository.save(post);

        return new ResponseEntity<>(
            post,
            HttpStatus.CREATED
        );
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<Post>> index(@RequestParam(required = false) Integer authorId)
    {
        if (authorId == null) {
            return new ResponseEntity<>(
                postRepository.findAll(),
                HttpStatus.OK
            );
        }

        Optional<User> optionalAuthor = this.userRepository.findById(authorId);

        if (optionalAuthor.isEmpty()) {
            throw new UserNotFoundException();
        }

        return new ResponseEntity<>(
            postRepository.findAllByAuthor(optionalAuthor.get()),
            HttpStatus.OK
        );
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Iterable<Post>> all()
    {
        return new ResponseEntity<>(
            postRepository.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Post> show(@PathVariable Integer id)
    {
        return postRepository.findById(id)
            .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequireAuthentication
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        return postRepository.findById(id)
            .map(post -> {
                postRepository.delete(post);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Post> update(@PathVariable Integer id, @RequestBody UpdatePostRequest request)
    {
        Optional<Post> optionalPost = this.postRepository.findById(id);
        User user = this.authenticationService.authenticate();

        if (optionalPost.isEmpty()) {
            throw new PostNotFoundException();
        }

        Post post = optionalPost.get();

        if (post.getAuthor().equals(user)) {
            throw new ForbiddenException();
        }

        post.setTitle(request.title());
        post.setContent(request.content());
        post.setActive(request.active());

        postRepository.save(post);

        return new ResponseEntity<>(
            post,
            HttpStatus.OK
        );
    }
}