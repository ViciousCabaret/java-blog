package wsb.studenci.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.request.post.CreatePostRequest;
import wsb.studenci.blog.repository.PostRepository;

@Controller
@RequestMapping(path="/posts")
public class PostController {
    private @Autowired PostRepository postRepository;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Post> create(@RequestBody CreatePostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        postRepository.save(post);

        return new ResponseEntity<>(
                post,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<Post>> index() {
        return new ResponseEntity<>(
                postRepository.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Post> show(@PathVariable Integer id) {
        return postRepository.findById(id)
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return postRepository.findById(id)
                .map(post -> {
                    postRepository.delete(post);

                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Post> update(@PathVariable Integer id, @RequestBody CreatePostRequest request) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(request.getTitle());
                    post.setContent(request.getContent());
                    postRepository.save(post);

                    return new ResponseEntity<>(post, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}