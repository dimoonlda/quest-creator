package ua.kiev.dimoon.questcreator.front.base.dto;

public class UserDto {
    private Integer id;
    private String firstName;
    private String userLogin;
    private QuestDto currentQuest;

    public Integer getId() {
        return id;
    }

    public UserDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public UserDto setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    public QuestDto getCurrentQuest() {
        return currentQuest;
    }

    public UserDto setCurrentQuest(QuestDto currentQuest) {
        this.currentQuest = currentQuest;
        return this;
    }
}
