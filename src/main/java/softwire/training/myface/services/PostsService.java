package softwire.training.myface.services;

import org.jdbi.v3.core.Handle;
import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Post;

import java.util.List;

@Service
public class PostsService extends DatabaseService {

    public List<Post> getPostsOnWall(String recipient) {
        try (Handle handle = jdbi.open()) {
            return handle
                    .createQuery("SELECT * FROM posts WHERE recipient = :recipient")
                    .bind("recipient", recipient)
                    .mapToBean(Post.class)
                    .list();
        }
    }

    public void createPost(String sender, String recipient, String content) {
        jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO posts (sender, recipient, content) VALUES (:sender, :recipient, :content)")
                        .bind("sender", sender)
                        .bind("recipient", recipient)
                        .bind("content", content)
                        .execute()
        );
    }
}
