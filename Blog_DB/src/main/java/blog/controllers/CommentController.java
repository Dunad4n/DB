package blog.controllers;

import blog.entities.Comment;
import blog.repositories.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("http://localhost:8081/")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/all")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment findById(@PathVariable("id") Comment comment) {
        return comment;
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("{id}")
    public Comment update(@PathVariable("id") Comment commentFromDb, @RequestBody Comment comment) {
        BeanUtils.copyProperties(comment, commentFromDb, "id", "date", "user", "post");

        return commentRepository.save(commentFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Comment comment) {
        commentRepository.delete(comment);
    }

}
