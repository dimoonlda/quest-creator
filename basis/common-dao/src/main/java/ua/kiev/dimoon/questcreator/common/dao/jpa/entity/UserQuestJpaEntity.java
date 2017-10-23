package ua.kiev.dimoon.questcreator.common.dao.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_quest")
public class UserQuestJpaEntity {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserJpaEntity user;
    @ManyToOne
    @JoinColumn(name="quest_id", nullable=false)
    private QuestJpaEntity quest;
    @ManyToOne
    @JoinColumn(name="quest_step_id", nullable=false)
    private QuestStepJpaEntity questStep;
    @Column(name = "is_completed")
    private Boolean completed = false;

    public Integer getId() {
        return id;
    }

    public UserQuestJpaEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserJpaEntity getUser() {
        return user;
    }

    public UserQuestJpaEntity setUser(UserJpaEntity user) {
        this.user = user;
        return this;
    }

    public QuestJpaEntity getQuest() {
        return quest;
    }

    public UserQuestJpaEntity setQuest(QuestJpaEntity quest) {
        this.quest = quest;
        return this;
    }

    public QuestStepJpaEntity getQuestStep() {
        return questStep;
    }

    public UserQuestJpaEntity setQuestStep(QuestStepJpaEntity questStep) {
        this.questStep = questStep;
        return this;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public UserQuestJpaEntity setCompleted(Boolean completed) {
        this.completed = completed;
        return this;
    }
}
