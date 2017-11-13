package ua.kiev.dimoon.questcreator.common.dao.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quest_step_fields")
public class QuestStepFieldJpaEntity {

    @Id
    @SequenceGenerator(sequenceName = "quest_step_fields_id_seq", name = "quest_ste_field_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quest_ste_field_gen")
    private Integer id;
    private String title;
    @NotNull
    @Column(name = "field_value", nullable = false)
    private String value;
    @Enumerated
    @NotNull
    @Column(name = "field_type_id", nullable = false)
    private FieldType fieldType;
    @Enumerated
    @NotNull
    @Column(name = "field_data_type_id", nullable = false)
    private FieldDataType fieldDataType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_step_id", nullable = false)
    private QuestStepJpaEntity questStep;

    public Integer getId() {
        return id;
    }

    public QuestStepFieldJpaEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QuestStepFieldJpaEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getValue() {
        return value;
    }

    public QuestStepFieldJpaEntity setValue(String value) {
        this.value = value;
        return this;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public QuestStepFieldJpaEntity setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public FieldDataType getFieldDataType() {
        return fieldDataType;
    }

    public QuestStepFieldJpaEntity setFieldDataType(FieldDataType fieldDataType) {
        this.fieldDataType = fieldDataType;
        return this;
    }

    public QuestStepJpaEntity getQuestStep() {
        return questStep;
    }

    public QuestStepFieldJpaEntity setQuestStep(QuestStepJpaEntity questStep) {
        this.questStep = questStep;
        return this;
    }
}
