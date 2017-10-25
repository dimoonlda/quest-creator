package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.front.base.dto.QuestStepDto;
import ua.kiev.dimoon.questcreator.services.quest.service.QuestService;

import java.util.Optional;

/**
 * Created by dlutai on 23.10.17.
 */
@Controller
public class DoQuestController {

    @Autowired private QuestService questService;
    @Autowired private DtoBuilder dtoBuilder;

    @RequestMapping(value = "/doQuest", method = RequestMethod.GET)
    public String doQuest(Model model) {
        model.addAttribute("questStepAnswer", new QuestStepAnswerFormResult());
        Optional<QuestStepJpaEntity> nextQuestStep = questService.getCurrentQuestStepForCurrentUser();
        if (nextQuestStep.isPresent()) {
            QuestStepDto questStep = dtoBuilder.getQuestStepDto(nextQuestStep.get());
            model.addAttribute("questStep", questStep);
        }
        return "doQuest";
    }

    @RequestMapping(value = "/doQuest", method = RequestMethod.POST)
    public String doQuest(@ModelAttribute QuestStepAnswerFormResult questStepAnswer, BindingResult bindingResult, Model model) {
        Boolean checkResult = questService.checkCurrentStepAnswer(questStepAnswer.getKeyWord());
        if (!checkResult) {
            Optional<QuestStepJpaEntity> nextQuestStep = questService.getCurrentQuestStepForCurrentUser();
            if (nextQuestStep.isPresent()) {
                QuestStepDto questStep = dtoBuilder.getQuestStepDto(nextQuestStep.get());
                model.addAttribute("questStep", questStep);
            }
            bindingResult.addError(new ObjectError("keyWord", "Wrong answer!!!"));
            model.addAttribute("questStepAnswer", questStepAnswer);
            return "doQuest";
        }
        return "redirect:/doQuest";
    }

    static class QuestStepAnswerFormResult {
        private String keyWord;

        public String getKeyWord() {
            return keyWord;
        }

        public QuestStepAnswerFormResult setKeyWord(String keyWord) {
            this.keyWord = keyWord;
            return this;
        }
    }
}
