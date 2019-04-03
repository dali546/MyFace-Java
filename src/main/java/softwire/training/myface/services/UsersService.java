package softwire.training.myface.services;

import org.jdbi.v3.core.Handle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService extends DatabaseService {

    public List<String> guessAllUsernames() {
        try (Handle handle = jdbi.open()) {
            return handle
                    .createQuery("(SELECT DISTINCT recipient FROM posts) UNION (SELECT DISTINCT sender FROM posts)")
                    .mapTo(String.class)
                    .list();
        }
    }

}
