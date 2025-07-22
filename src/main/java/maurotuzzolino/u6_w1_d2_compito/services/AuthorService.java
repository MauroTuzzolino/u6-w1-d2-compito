package maurotuzzolino.u6_w1_d2_compito.services;

import maurotuzzolino.u6_w1_d2_compito.entities.Author;
import maurotuzzolino.u6_w1_d2_compito.payloads.NewAuthorPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AuthorService {
    private List<Author> authors = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    public List<Author> findAll() {
        return authors;
    }

    public Author save(NewAuthorPayload body) {
        Author author = new Author();
        author.setId(idCounter.incrementAndGet());
        author.setNome(body.getNome());
        author.setCognome(body.getCognome());
        author.setEmail(body.getEmail());
        author.setDataDiNascita(body.getDataDiNascita());
        author.setAvatar("https://api.multiavatar.com/" + body.getNome() + ".png");
        authors.add(author);
        return author;
    }

    public Author findById(long id) {
        return authors.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Autore non trovato"));
    }

    public Author updateById(long id, NewAuthorPayload body) {
        Author author = findById(id);
        author.setNome(body.getNome());
        author.setCognome(body.getCognome());
        author.setEmail(body.getEmail());
        author.setDataDiNascita(body.getDataDiNascita());
        return author;
    }

    public void deleteById(long id) {
        Author author = findById(id);
        authors.remove(author);
    }
}