package softwire.training.myface.models.viewmodels;

import softwire.training.myface.models.dbmodels.Post;
import softwire.training.myface.models.dbmodels.User;

import java.util.List;

public class WallViewModel {

    private List<Post> posts;
    private User loggedInUser;
    private User wallUser;

    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getWallUser() {
        return wallUser;
    }
    public void setWallUser(User wallUser) {
        this.wallUser = wallUser;
    }
}
