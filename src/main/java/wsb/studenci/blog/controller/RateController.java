package wsb.studenci.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsb.studenci.blog.exception.post.PostNotFoundException;
import wsb.studenci.blog.exception.rate.RateNotFoundException;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.Rate;
import wsb.studenci.blog.model.User;
import wsb.studenci.blog.model.request.rate.CreateRatePostModel;
import wsb.studenci.blog.model.request.rate.DeleteRatePostModel;
import wsb.studenci.blog.repository.PostRepository;
import wsb.studenci.blog.repository.RateRepository;
import wsb.studenci.blog.service.AuthenticationService;
import wsb.studenci.blog.service.RateService;
import wsb.studenci.blog.service.annotation.authentication.RequireAuthentication;

import java.util.Optional;

@Controller
@RequestMapping(path = "/rates")
public class RateController
{
    private final RateRepository rateRepository;
    private final RateService rateService;
    private final PostRepository postRepository;
    private final AuthenticationService authenticationService;

    public RateController(
        RateRepository rateRepository,
        PostRepository postRepository,
        AuthenticationService authenticationService,
        RateService rateService
    ) {
        this.rateRepository = rateRepository;
        this.postRepository = postRepository;
        this.authenticationService = authenticationService;
        this.rateService = rateService;
    }


    @RequireAuthentication
    @PutMapping
    @ResponseBody
    public ResponseEntity<Rate> create(@RequestBody CreateRatePostModel request)
    {
        Optional<Post> optionalPost = postRepository.findById(request.getPostId());
        if (optionalPost.isEmpty()) {
            throw new PostNotFoundException();
        }

        User user = this.authenticationService.authenticate();

        Optional<Rate> optionalRate = this.rateRepository.findByAuthorAndPost(user, optionalPost.get());
        if (optionalRate.isEmpty()) {
            Rate rate = new Rate(
                user,
                optionalPost.get(),
                request.getScore()
            );

            this.rateRepository.save(rate);
            return new ResponseEntity<>(rate, HttpStatus.CREATED);
        }
        Rate rate = optionalRate.get();
        rate.setScore(request.getScore());

        this.rateRepository.save(rate);

        this.rateService.recalculatePostRating(optionalPost.get());

        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @RequireAuthentication
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody DeleteRatePostModel request)
    {
        Optional<Post> optionalPost = postRepository.findById(request.getPostId());
        if (optionalPost.isEmpty()) {
            throw new PostNotFoundException();
        }

        User user = this.authenticationService.authenticate();

        Optional<Rate> optionalRate = this.rateRepository.findByAuthorAndPost(user, optionalPost.get());

        if (optionalRate.isEmpty()) {
            throw new RateNotFoundException();
        }

        this.rateRepository.delete(optionalRate.get());
        this.rateService.recalculatePostRating(optionalPost.get());

        return ResponseEntity.noContent().build();
    }
}
