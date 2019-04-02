package softwire.training.myface.database;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.Post;

import java.util.List;

@Service
public class DatabaseService {

    private Jdbi jdbi;

    DatabaseService() {
        String hostname = "localhost";
        String databaseName = "myface";
        String port = "3306";
        String username = "myfaceuser";
        String password = "myfacepassword";

        String connectionString = "jdbc:mysql://" + hostname +
                ":" + port +
                "/" + databaseName +
                "?user=" + username +
                "&password=" + password +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbi = Jdbi.create(connectionString);
    }


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

    public List<String> guessAllUsernames() {
        try (Handle handle = jdbi.open()) {
            return handle
                    .createQuery("(SELECT DISTINCT recipient FROM posts) UNION (SELECT DISTINCT sender FROM posts)")
                    .mapTo(String.class)
                    .list();
        }
    }
}
