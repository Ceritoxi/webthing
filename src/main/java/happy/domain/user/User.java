package happy.domain.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private byte[] hash;
    @NotNull
    private byte[] salt;
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private PersonalConfigurations personalConfigurations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public PersonalConfigurations getPersonalConfigurations() {
        return personalConfigurations;
    }

    public void setPersonalConfigurations(PersonalConfigurations personalConfigurations) {
        this.personalConfigurations = personalConfigurations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Arrays.equals(hash, user.hash) &&
                Arrays.equals(salt, user.salt) &&
                Objects.equals(personalConfigurations, user.personalConfigurations);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, email, personalConfigurations);
        result = 31 * result + Arrays.hashCode(hash);
        result = 31 * result + Arrays.hashCode(salt);
        return result;
    }
}
