package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestStepService;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping(value = "/quests/steps")
public class QuestStepsController {

    private QuestStepService questStepService;
    private DtoBuilder dtoBuilder;

    public QuestStepsController(QuestStepService questStepService,
                                DtoBuilder dtoBuilder) {
        this.questStepService = questStepService;
        this.dtoBuilder = dtoBuilder;
    }

    @RequestMapping(value = "/view/{questStepId}", method = RequestMethod.GET)
    public String viewQuestStepInfo(@PathVariable Integer questStepId, Model model) {
        questStepService.findOne(questStepId)
                .ifPresent(questStepEntity -> {
                            model.addAttribute(
                                    "questStep",
                                    dtoBuilder.getQuestStepDto(questStepEntity));
                        }
                );

        return "/quests/steps/view";
    }
}
