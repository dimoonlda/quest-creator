package ua.kiev.dimoon.questcreator.rest.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestJpaEntity;
import ua.kiev.dimoon.questcreator.services.quest.service.QuestService;

import java.util.List;

@Controller
public class IndexController {

    private QuestService questService;

    public IndexController(QuestService questService) {
        this.questService = questService;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        List<QuestJpaEntity> quests = questService.getQuestsForCurrentUser();
        return "index";
    }
}
