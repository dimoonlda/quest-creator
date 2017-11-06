package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.front.base.dto.QuestDto;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestService;

import java.util.List;

@Controller
@RequestMapping(value = "/quests")
public class QuestsController {

    private QuestService questService;
    private DtoBuilder dtoBuilder;

    public QuestsController(QuestService questService,
                            DtoBuilder dtoBuilder) {
        this.questService = questService;
        this.dtoBuilder = dtoBuilder;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    public String showQuests(Model model) {
        List<QuestDto> quests = dtoBuilder.getQuestDtos(questService.getQuests());
        model.addAttribute("quests", quests);
        return "/quests/all";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{questId}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable Integer questId) {
        questService.delete(questId);
        return "redirect:/quests";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/view/{questId}", method = RequestMethod.GET)
    public String viewQuestInfo(@PathVariable Integer questId, Model model) {
        questService.findQuestById(questId)
                .ifPresent(
                        questEntity -> model.addAttribute("quest", dtoBuilder.getQuestDto(questEntity))
                );
        return "/quests/view";
    }
}
