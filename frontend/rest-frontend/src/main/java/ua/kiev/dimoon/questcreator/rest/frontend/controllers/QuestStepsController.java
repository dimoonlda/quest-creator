package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.FieldDataType;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.StepType;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.front.base.dto.QuestDto;
import ua.kiev.dimoon.questcreator.front.base.dto.QuestStepDto;
import ua.kiev.dimoon.questcreator.front.base.dto.QuestStepFieldDto;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestService;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestStepService;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping(value = "/quests/{questId}/steps")
public class QuestStepsController {

    private QuestStepService questStepService;
    private QuestService questService;
    private DtoBuilder dtoBuilder;

    public QuestStepsController(QuestStepService questStepService,
                                QuestService questService,
                                DtoBuilder dtoBuilder) {
        this.questStepService = questStepService;
        this.questService = questService;
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
        model.addAttribute("fieldDataTypes", FieldDataType.values());
        model.addAttribute("questStepField", new QuestStepFieldDto());
        model.addAttribute("stepTypes", StepType.values());
        return "/quests/steps/view";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveQuestStepField(@ModelAttribute(value = "questStep") QuestStepDto stepDto,
                                     @PathVariable(value = "questId") Integer questId) {
        QuestStepJpaEntity questStepEntity;
        if (null != stepDto.getId()) {
            questStepEntity = questStepService.findOne(stepDto.getId())
                    .orElse(new QuestStepJpaEntity());
        } else {
            questStepEntity = new QuestStepJpaEntity();
            questStepEntity.setQuest(questService.findById(questId));
        }
        questStepService.save(
                questStepEntity
                        .setOrder(stepDto.getOrder())
                        .setStepType(stepDto.getStepType())
        );
        return "redirect:/quests/" + questId + "/steps/view/" + questStepEntity.getId();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateModalForStepField(@PathVariable(value = "questId") Integer questId,
                                              Model model) {
        model.addAttribute("stepTypes", StepType.values());
        model.addAttribute("questStep",
                new QuestStepDto().setQuest(new QuestDto().setId(questId))
        );
        return "/quests/steps/view :: questStepModal";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{stepId}/delete", method = RequestMethod.POST)
    public String deleteStep(@PathVariable(value = "stepId") Integer stepId,
                              @PathVariable(value = "questId") Integer questId) {
        questStepService.delete(stepId);
        return String.format("redirect:/quests/view/%s", questId);
    }


}
