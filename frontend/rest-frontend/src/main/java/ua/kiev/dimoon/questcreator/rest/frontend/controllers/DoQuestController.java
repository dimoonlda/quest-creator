package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.front.base.dto.QuestStepDto;
import ua.kiev.dimoon.questcreator.services.quest.service.QuestService;

import java.util.Optional;

/**
 * Created by dlutai on 23.10.17.
 */
@Controller
@RequestMapping(value = "/doQuest")
public class DoQuestController {

    @Autowired private QuestService questService;
    @Autowired private DtoBuilder dtoBuilder;

    @RequestMapping(method = RequestMethod.GET)
    public String doQuest(@ModelAttribute("checkResult") final Object checkResult,
                          @ModelAttribute("questStepAnswer") QuestStepAnswerForm questStepAnswer,
                          Model model) {
        Optional<QuestStepJpaEntity> nextQuestStep = questService.getCurrentQuestStepForCurrentUser();
        if (nextQuestStep.isPresent()) {
            QuestStepDto questStep = dtoBuilder.getQuestStepDto(nextQuestStep.get());
            if (Boolean.TRUE.equals(checkResult)) {
                questStepAnswer.setKeyWord(null);
            }
            questStepAnswer.setQuestStep(questStep);
        }
        return "doQuest";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doQuest(@ModelAttribute QuestStepAnswerForm questStepAnswer,
                          final RedirectAttributes redirectAttributes) {
        Boolean checkResult = questService.checkCurrentStepAnswer(questStepAnswer.getKeyWord());
        redirectAttributes.addFlashAttribute("checkResult", checkResult);
        redirectAttributes.addFlashAttribute("questStepAnswer", questStepAnswer);
        return "redirect:/doQuest";
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String finishQuest() {
        questService.finishQuestForCurrentUser();
        return "redirect:/";
    }

    static class QuestStepAnswerForm {
        private String keyWord;
        private QuestStepDto questStep;

        public String getKeyWord() {
            return keyWord;
        }

        public QuestStepAnswerForm setKeyWord(String keyWord) {
            this.keyWord = keyWord;
            return this;
        }

        public QuestStepDto getQuestStep() {
            return questStep;
        }

        public QuestStepAnswerForm setQuestStep(QuestStepDto questStep) {
            this.questStep = questStep;
            return this;
        }
    }
}
