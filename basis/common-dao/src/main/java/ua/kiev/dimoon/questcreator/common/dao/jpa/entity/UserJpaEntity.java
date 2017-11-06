package ua.kiev.dimoon.questcreator.common.dao.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    @SequenceGenerator(name = "user_gen_id", sequenceName = "users_id_seq", allocationSize = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_gen_id")
    private Integer id;
    @Column(name = "first_name", nullable = false)
    @NotNull
    @Size(min = 3)
    private String firstName;
    @Column(name = "user_login", nullable = false)
    @NotNull
    @Size(min = 3)
    private String userLogin;
    @Column(name = "user_password", nullable = false)
    @NotNull
    private String userPassword;
    @OneToMany(mappedBy = "user")
    private Set<UserQuestJpaEntity> quests;

    public Integer getId() {
        return id;
    }

    public UserJpaEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserJpaEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public UserJpaEntity setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public UserJpaEntity setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public Set<UserQuestJpaEntity> getQuests() {
        return quests;
    }

    public UserJpaEntity setQuests(Set<UserQuestJpaEntity> quests) {
        this.quests = quests;
        return this;
    }
}
