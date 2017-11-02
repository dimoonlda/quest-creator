package ua.kiev.dimoon.questcreator.quest.service.api.services;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserQuestJpaEntity;

import java.util.List;
import java.util.Optional;

public interface QuestService {
    List<UserQuestJpaEntity> getQuestsForCurrentUser();
    void startQuestForCurrentUser(int questId);
    Boolean checkCurrentStepAnswer(String keyWord);
    Optional<QuestStepJpaEntity> getCurrentQuestStepForCurrentUser();
    void finishQuestForCurrentUser();
}
