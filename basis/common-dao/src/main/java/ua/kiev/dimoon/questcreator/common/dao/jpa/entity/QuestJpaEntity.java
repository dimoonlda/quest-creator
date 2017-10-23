package ua.kiev.dimoon.questcreator.common.dao.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by dlutai on 23.10.17.
 */
@Entity
@Table(name = "quests")
public class QuestJpaEntity {
    @Id
    private Integer id;
    @NotNull
    private String tittle;
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id")
    private Set<QuestStepJpaEntity> questSteps;

    public Integer getId() {
        return id;
    }

    public QuestJpaEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public QuestJpaEntity setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QuestJpaEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<QuestStepJpaEntity> getQuestSteps() {
        return questSteps;
    }

    public QuestJpaEntity setQuestSteps(Set<QuestStepJpaEntity> questSteps) {
        this.questSteps = questSteps;
        return this;
    }
}
