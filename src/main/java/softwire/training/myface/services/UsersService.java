package softwire.training.myface.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softwire.training.myface.models.dbmodels.User;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService extends DatabaseService {

    private final PasswordEncoder encoder;

    @Autowired
    public UsersService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public List<User> getAllUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users")
                        .mapToBean(User.class)
                        .list());
    }


    public void createUser(User user) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO users (username, password, first_name, last_name) " +
                        "VALUES (:username, :password, :first_name, :last_name)")
                        .bind("username", user.getUsername())
                        .bind("password", encoder.encode(user.getPassword()))
                        .bind("first_name", user.getFirst_name())
                        .bind("last_name", user.getLast_name())
                        .execute()
        );
    }

    public Optional<String> getEncodedPassword(String username) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT password FROM users WHERE username = :username")
                        .bind("username", username)
                        .mapTo(String.class)
                        .findFirst()
        );
    }

    public User getUser(int i) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE id = :id")
                        .bind("id", i)
                        .mapToBean(User.class)
                        .findFirst().get()
        );
    }


    public User getUserByUsername(String username) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE username = :username")
                        .bind("username", username)
                        .mapToBean(User.class)
                        .findFirst().get()
        );
    }
}
