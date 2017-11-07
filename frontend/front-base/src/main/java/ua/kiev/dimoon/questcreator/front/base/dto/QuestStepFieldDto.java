package ua.kiev.dimoon.questcreator.front.base.dto;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.FieldDataType;

public class QuestStepFieldDto {
    private int id;
    private String value;
    private String title;
    private FieldDataType fieldDataType;

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

    public int getId() {
        return id;
    }

    public QuestStepFieldDto setId(int id) {
        this.id = id;
        return this;
    }
}
