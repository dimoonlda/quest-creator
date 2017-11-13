package ua.kiev.dimoon.questcreator.front.base.dto;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.FieldDataType;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.FieldType;

public class QuestStepFieldDto {
    private Integer id;
    private String value;
    private String title;
    private FieldDataType fieldDataType;
    private FieldType fieldType;

    public String getValue() {
        return value;
    }

    public QuestStepFieldDto setValue(String value) {
        this.value = value;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QuestStepFieldDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public FieldDataType getFieldDataType() {
        return fieldDataType;
    }

    public QuestStepFieldDto setFieldDataType(FieldDataType fieldDataType) {
        this.fieldDataType = fieldDataType;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public QuestStepFieldDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public QuestStepFieldDto setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
        return this;
    }
}
