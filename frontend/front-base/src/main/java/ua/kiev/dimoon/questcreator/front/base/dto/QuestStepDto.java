package ua.kiev.dimoon.questcreator.front.base.dto;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.StepType;

import java.util.List;

public class QuestStepDto {
    private int id;
    private QuestStepFieldDto question;
    private List<QuestStepFieldDto> auxiliaryDatas;
    private StepType stepType;
    private Boolean needAnswer;

    public int getId() {
        return id;
    }

    public QuestStepDto setId(int id) {
        this.id = id;
        return this;
    }

    public QuestStepFieldDto getQuestion() {
        return question;
    }

    public QuestStepDto setQuestion(QuestStepFieldDto question) {
        this.question = question;
        return this;
    }

    public List<QuestStepFieldDto> getAuxiliaryDatas() {
        return auxiliaryDatas;
    }

    public QuestStepDto setAuxiliaryDatas(List<QuestStepFieldDto> auxiliaryDatas) {
        this.auxiliaryDatas = auxiliaryDatas;
        return this;
    }

    public StepType getStepType() {
        return stepType;
    }

    public QuestStepDto setStepType(StepType stepType) {
        this.stepType = stepType;
        return this;
    }

    public Boolean getNeedAnswer() {
        return needAnswer;
    }

    public QuestStepDto setNeedAnswer(Boolean needAnswer) {
        this.needAnswer = needAnswer;
        return this;
    }
}
