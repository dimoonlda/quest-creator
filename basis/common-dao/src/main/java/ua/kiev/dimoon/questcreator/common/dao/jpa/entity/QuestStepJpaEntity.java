package ua.kiev.dimoon.questcreator.common.dao.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.Set;

/**
 * Created by dlutai on 23.10.17.
 */
@Entity
@Table(name = "quest_steps")
public class QuestStepJpaEntity {
    @Id
    private Integer id;
    @Column(name = "step_order", nullable = false)
    @NotNull
    private Integer order;
    @Enumerated
    @Column(name = "step_type_id", nullable = false)
    @NotNull
    private StepType stepType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "questStep")
    private Set<QuestStepFieldJpaEntity> fields;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id", nullable = false)
    private QuestJpaEntity quest;

    public static final Comparator<QuestStepJpaEntity> SORT_BY_ODER = Comparator.comparing(QuestStepJpaEntity::getOrder);

    public Integer getId() {
        return id;
    }

    public QuestStepJpaEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public QuestStepJpaEntity setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public StepType getStepType() {
        return stepType;
    }

    public QuestStepJpaEntity setStepType(StepType stepType) {
        this.stepType = stepType;
        return this;
    }

    public Set<QuestStepFieldJpaEntity> getFields() {
        return fields;
    }

    public QuestStepJpaEntity setFields(Set<QuestStepFieldJpaEntity> fields) {
        this.fields = fields;
        return this;
    }

    public boolean isStartStep() {
        return StepType.START.equals(this.getStepType());
    }

    public boolean isEndStep() {
        return StepType.END.equals(this.getStepType());
    }

    public QuestJpaEntity getQuest() {
        return quest;
    }

    public QuestStepJpaEntity setQuest(QuestJpaEntity quest) {
        this.quest = quest;
        return this;
    }
}
