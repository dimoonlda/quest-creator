package ua.kiev.dimoon.questcreator.front.base.dto;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.StepType;

import java.util.List;

public class QuestStepDto {
    private Integer id;
    private QuestStepFieldDto question;
    private List<QuestStepFieldDto> auxiliaryDatas;
    private StepType stepType;
    private Boolean needAnswer;
    private QuestStepFieldDto answer;
    private Integer order;
    private QuestDto quest;

    public Integer getId() {
        return id;
    }

    public QuestStepDto setId(Integer id) {
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

    public Integer getOrder() {
        return order;
    }

    public QuestStepDto setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public QuestStepFieldDto getAnswer() {
        return answer;
    }

    public QuestStepDto setAnswer(QuestStepFieldDto answer) {
        this.answer = answer;
        return this;
    }

    public QuestDto getQuest() {
        return quest;
    }

    public QuestStepDto setQuest(QuestDto quest) {
        this.quest = quest;
        return this;
    }
}
