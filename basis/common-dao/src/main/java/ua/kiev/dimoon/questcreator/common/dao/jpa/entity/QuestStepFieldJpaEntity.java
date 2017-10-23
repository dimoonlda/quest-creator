package ua.kiev.dimoon.questcreator.common.dao.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quest_step_fields")
public class QuestStepFieldJpaEntity {

    @Id
    private Integer id;
    private String tittle;
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

    public Integer getId() {
        return id;
    }

    public QuestStepFieldJpaEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public QuestStepFieldJpaEntity setTittle(String tittle) {
        this.tittle = tittle;
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
}
