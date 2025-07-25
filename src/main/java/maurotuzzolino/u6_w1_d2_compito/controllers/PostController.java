package maurotuzzolino.u6_w1_d2_compito.controllers;

import maurotuzzolino.u6_w1_d2_compito.entities.Post;
import maurotuzzolino.u6_w1_d2_compito.payloads.NewPostPayload;
import maurotuzzolino.u6_w1_d2_compito.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // 1. GET /posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    // 2. POST /posts
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody NewPostPayload body) {
        return postService.save(body);
    }

    // 3. GET /posts/{id}
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable long id) {
        return postService.findById(id);
    }

    // 4. PUT /posts/{id}
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable long id, @RequestBody NewPostPayload body) {
        return postService.updateById(id, body);
    }

    // 5. DELETE /posts/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable long id) {
        postService.deleteById(id);
    }
}