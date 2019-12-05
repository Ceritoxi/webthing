package happy.domain.record;

import happy.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
public class Record {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @NotNull
    private User user;
    @NotNull
    private ZonedDateTime timestamp;
    @Enumerated(EnumType.STRING)
    @NotNull
    private State state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
