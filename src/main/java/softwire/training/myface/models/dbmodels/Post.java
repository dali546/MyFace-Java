package softwire.training.myface.models.dbmodels;

import org.jdbi.v3.core.mapper.Nested;

public class Post {

    private int id;
    private User sender = new User();
    private User recipient = new User();
    private String content;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Nested("sender")
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }

    @Nested("recipient")
    public User getRecipient() {
        return recipient;
    }
    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }


}
