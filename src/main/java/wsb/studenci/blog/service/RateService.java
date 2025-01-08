package wsb.studenci.blog.service;

import org.springframework.stereotype.Service;
import wsb.studenci.blog.model.Post;
import wsb.studenci.blog.model.Rate;
import wsb.studenci.blog.repository.PostRepository;
import wsb.studenci.blog.repository.RateRepository;

import java.util.Iterator;
import java.util.List;

@Service
public class RateService
{
    private final RateRepository rateRepository;
    private final PostRepository postRepository;

    public RateService(RateRepository rateRepository, PostRepository postRepository)
    {
        this.rateRepository = rateRepository;
        this.postRepository = postRepository;
    }

    public void recalculatePostRating(Post post)
    {
        List<Rate> rates = this.rateRepository.findByPost(post);
        int counter = 0;
        Integer sum = 0;
        for (Rate rate : rates) {
            sum += rate.getScore();
            counter++;
        }

        post.setRate((float) sum / counter);

        this.postRepository.save(post);
    }
}
