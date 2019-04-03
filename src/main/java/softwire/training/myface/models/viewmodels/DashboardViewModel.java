package softwire.training.myface.models.viewmodels;

import softwire.training.myface.models.dbmodels.User;

import java.util.List;

public class DashboardViewModel {

    private List<User> users;
    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
