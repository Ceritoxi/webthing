package happy.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Objects;

@Entity
public class PersonalConfigurations {

    private static final int DEFAULT_DURATION_HOURS = 8;
    private static final int DEFAULT_DURATION_MINUTES = 30;

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private Duration desiredGoal;

    public static PersonalConfigurations createDefault() {
        PersonalConfigurations personalConfigurations = new PersonalConfigurations();
        personalConfigurations.setDesiredGoal(Duration.ofHours(DEFAULT_DURATION_HOURS).plusMinutes(DEFAULT_DURATION_MINUTES));
        return personalConfigurations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Duration getDesiredGoal() {
        return desiredGoal;
    }

    public void setDesiredGoal(Duration desiredGoal) {
        this.desiredGoal = desiredGoal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalConfigurations that = (PersonalConfigurations) o;
        return id == that.id &&
                Objects.equals(desiredGoal, that.desiredGoal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desiredGoal);
    }
}
