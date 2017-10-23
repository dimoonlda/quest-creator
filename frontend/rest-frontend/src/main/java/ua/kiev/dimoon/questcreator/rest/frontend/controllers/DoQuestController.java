package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dlutai on 23.10.17.
 */
@Controller
public class DoQuestController {

    @RequestMapping("/doQuest")
    public String doQuest(Model model) {
        QuestStep questStep = new QuestStep()
                .setType(2)
                .setQuestion(
                        new QuestStepField().setTittle("Ключ тут").setValue("a[3]a[7]a[0]a[12]a[7]")
                )
                .setAuxiliaryDatas(
                        Arrays.asList(
                                new QuestStepField().setTittle("вхідний масив").setValue("a[] = 'ЗТ1БАМРГФОУЯЛК'"),
                                new QuestStepField().setTittle("підказка").setValue("пароль на листку")
                        )
                );
        model.addAttribute("questStep", questStep);
        return "doQuest";
    }

    private class QuestStepField {
        private String value;
        private String tittle;
        private String type;

        public String getValue() {
            return value;
        }

        public QuestStepField setValue(String value) {
            this.value = value;
            return this;
        }

        public String getTittle() {
            return tittle;
        }

        public QuestStepField setTittle(String tittle) {
            this.tittle = tittle;
            return this;
        }

        public String getType() {
            return type;
        }

        public QuestStepField setType(String type) {
            this.type = type;
            return this;
        }
    }

    private class QuestStep {
        private int id;
        private QuestStepField question;
        private List<QuestStepField> auxiliaryDatas;
        private int type;

        public QuestStepField getQuestion() {
            return question;
        }

        public QuestStep setQuestion(QuestStepField question) {
            this.question = question;
            return this;
        }

        public List<QuestStepField> getAuxiliaryDatas() {
            return auxiliaryDatas;
        }

        public QuestStep setAuxiliaryDatas(List<QuestStepField> auxiliaryDatas) {
            this.auxiliaryDatas = auxiliaryDatas;
            return this;
        }

        public int getType() {
            return type;
        }

        public QuestStep setType(int type) {
            this.type = type;
            return this;
        }

        public int getId() {
            return id;
        }

        public QuestStep setId(int id) {
            this.id = id;
            return this;
        }
    }
}
