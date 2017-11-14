package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.FieldDataType;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepFieldJpaEntity;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.front.base.dto.QuestStepFieldDto;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestStepFieldService;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestStepService;

@Controller
@RequestMapping(value = "/quests/steps/{stepId}/fields")
public class QuestStepFieldsController {

    private DtoBuilder dtoBuilder;
    private QuestStepFieldService questStepFieldService;
    private QuestStepService questStepService;

    public QuestStepFieldsController(DtoBuilder dtoBuilder,
                                     QuestStepFieldService questStepFieldService,
                                     QuestStepService questStepService) {
        this.dtoBuilder = dtoBuilder;
        this.questStepFieldService = questStepFieldService;
        this.questStepService = questStepService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveQuestStepField(@ModelAttribute(value = "questStepField") QuestStepFieldDto fieldDto,
                                     @PathVariable(value = "stepId") Integer stepId) {
        QuestStepFieldJpaEntity questStepFieldEntity;
        if (null != fieldDto.getId()) {
            questStepFieldEntity = questStepFieldService.findOne(fieldDto.getId())
                    .orElse(new QuestStepFieldJpaEntity());
        } else {
            questStepFieldEntity = new QuestStepFieldJpaEntity();
        }
        questStepFieldService.save(
                questStepFieldEntity
                        .setQuestStep(questStepService.findById(stepId))
                        .setFieldDataType(fieldDto.getFieldDataType())
                        .setFieldType(fieldDto.getFieldType())
                        .setTitle(fieldDto.getTitle())
                        .setValue(fieldDto.getValue())
        );
        return "redirect:/quests/steps/view/" + stepId;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{fieldId}", method = RequestMethod.POST)
    public String deleteField(@PathVariable(value = "stepId") Integer stepId,
                              @PathVariable(value = "fieldId") Integer fieldId) {
        questStepFieldService.delete(fieldId);
        return "redirect:/quests/steps/view/" + stepId;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/view/{fieldId}", method = RequestMethod.GET)
    public String showModalForStepField(@PathVariable(value = "stepId") Integer stepId,
                                       @PathVariable(value = "fieldId") Integer fieldId,
                                       Model model) {
        QuestStepFieldJpaEntity questStepFieldEntity = questStepFieldService.findById(fieldId);
        model.addAttribute("questStepField", dtoBuilder.getQuestStepFieldDto(questStepFieldEntity));
        model.addAttribute("questStep", dtoBuilder.getQuestStepDto(questStepFieldEntity.getQuestStep()));
        model.addAttribute("fieldDataTypes", FieldDataType.values());
        return "/quests/steps/view :: questStepFieldModal";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateModalForStepField(@PathVariable(value = "stepId") Integer stepId,
                                        Model model) {
        model.addAttribute("questStepField", new QuestStepFieldDto());
        model.addAttribute("questStep", dtoBuilder.getQuestStepDto(questStepService.findById(stepId)));
        model.addAttribute("fieldDataTypes", FieldDataType.values());
        return "/quests/steps/view :: questStepFieldModal";
    }
}
