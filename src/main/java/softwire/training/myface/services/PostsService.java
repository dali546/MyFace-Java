package softwire.training.myface.services;

import org.jdbi.v3.core.Handle;
import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Post;
import softwire.training.myface.models.dbmodels.User;

import java.util.List;

@Service
public class PostsService extends DatabaseService {

    public List<Post> getPostsOnWall(String recipient) {
        return jdbi.withHandle(handle -> handle.createQuery
                ("SELECT posts.id id, posts.content content, " +
                        "sender.id sender_id, sender.username sender_username, sender.first_name sender_first_name, sender.last_name sender_last_name, " +
                        "recipient.id recipient_id, recipient.username recipient_username, recipient.first_name recipient_first_name, recipient.last_name recipient_last_name " +
                        "FROM posts " +
                        "INNER JOIN users AS sender ON posts.sender = sender.id " +
                        "INNER JOIN users AS recipient ON posts.recipient = recipient.id " +
                        "WHERE recipient.username = :recipient")
                .bind("recipient",recipient)
                .mapToBean(Post.class)
                .list()
        );
    }

    public void createPost(User sender, User recipient, String content) {
        jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO posts (sender, recipient, content) VALUES (:sender, :recipient, :content)")
                        .bind("sender", sender.getId())
                        .bind("recipient", recipient.getId())
                        .bind("content", content)
                        .execute()
        );
    }
}
