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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quest_gen_id")
    @SequenceGenerator(name = "quest_gen_id", sequenceName = "quests_id_seq", allocationSize = 10)
    private Integer id;
    @NotNull
    private String title;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quest")
    private Set<QuestStepJpaEntity> questSteps;

    public Integer getId() {
        return id;
    }

    public QuestJpaEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QuestJpaEntity setTitle(String title) {
        this.title = title;
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
