package blog.controllers;

import blog.entities.Post;
import blog.entities.User;
import blog.repositories.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("http://localhost:8081/")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/all")
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findPostById(@PathVariable("id")Post post) {
        return post;
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("{id}")
    public Post update(@PathVariable("id") Post postFromDb, @RequestBody Post post) {
        BeanUtils.copyProperties(post, postFromDb, "id", "date", "user");

        return postRepository.save(postFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Post post){
        postRepository.delete(post);
    }

}
