package softwire.training.myface.services;

import org.jdbi.v3.core.Jdbi;

public abstract class DatabaseService {

    protected final Jdbi jdbi;

    DatabaseService() {
        String hostname = "localhost";
        String databaseName = "myface";
        String port = "3306";
        String username = "root";
        String password = "root";

        String connectionString = "jdbc:mysql://" + hostname +
                ":" + port +
                "/" + databaseName +
                "?user=" + username +
                "&password=" + password +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbi = Jdbi.create(connectionString);
    }
}
