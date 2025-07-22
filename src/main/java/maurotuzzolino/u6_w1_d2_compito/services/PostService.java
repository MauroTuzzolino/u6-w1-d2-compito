package maurotuzzolino.u6_w1_d2_compito.services;

import maurotuzzolino.u6_w1_d2_compito.entities.Post;
import maurotuzzolino.u6_w1_d2_compito.payloads.NewPostPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    public List<Post> findAll() {
        return posts;
    }

    public Post save(NewPostPayload body) {
        Post post = new Post();
        post.setId(idCounter.incrementAndGet());
        post.setCategoria(body.getCategoria());
        post.setTitolo(body.getTitolo());
        post.setContenuto(body.getContenuto());
        post.setTempoDiLettura(body.getTempoDiLettura());
        post.setCover("https://picsum.photos/seed/" + body.getTitolo().replaceAll(" ", "-") + "/600/400");
        posts.add(post);
        return post;
    }

    public Post findById(long id) {
        return posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post non trovato"));
    }

    public Post updateById(long id, NewPostPayload body) {
        Post post = findById(id);
        post.setCategoria(body.getCategoria());
        post.setTitolo(body.getTitolo());
        post.setContenuto(body.getContenuto());
        post.setTempoDiLettura(body.getTempoDiLettura());
        return post;
    }

    public void deleteById(long id) {
        Post post = findById(id);
        posts.remove(post);
    }
}
