package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserQuestJpaEntity;
import ua.kiev.dimoon.questcreator.front.base.dto.DtoBuilder;
import ua.kiev.dimoon.questcreator.front.base.dto.QuestDto;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private QuestService questService;
    private DtoBuilder dtoBuilder;
    public IndexController(QuestService questService, DtoBuilder dtoBuilder) {
        this.questService = questService;
        this.dtoBuilder = dtoBuilder;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        List<UserQuestJpaEntity> questsForCurrentUser = questService.getQuestsForCurrentUser();
        final Optional<UserQuestJpaEntity> currentQuest = questsForCurrentUser.stream()
                .filter(userQuestJpaEntity -> null != userQuestJpaEntity.getQuestStep())
                .findFirst();
        if (currentQuest.isPresent() && !currentQuest.get().getCompleted()) {
            model.addAttribute("currentQuest", dtoBuilder.getQuestDto(currentQuest.get().getQuest()));
        } else {
            List<QuestDto> quests = dtoBuilder.getQuestDtos(
                    questsForCurrentUser.stream()
                            .map(UserQuestJpaEntity::getQuest)
                            .collect(Collectors.toList())
            );
            model.addAttribute("quests", quests);
            model.addAttribute("selectedQuest", new QuestFormResult());
        }
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String startQuest(@ModelAttribute QuestFormResult questFormResult) {
        if (null != questFormResult.getId()) {
            questService.startQuestForCurrentUser(questFormResult.getId());
            return "redirect:/doQuest";
        } else {
            return "redirect:/index";
        }
    }

    static class QuestFormResult {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public QuestFormResult setId(Integer id) {
            this.id = id;
            return this;
        }
    }
}
