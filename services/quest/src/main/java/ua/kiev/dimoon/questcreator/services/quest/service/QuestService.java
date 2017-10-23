package ua.kiev.dimoon.questcreator.services.quest.service;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;

import java.util.List;
import java.util.Optional;

public interface QuestService {
    List<QuestJpaEntity> getQuestsForCurrentUser();
    void startQuestForCurrentUser(int questId);
    Boolean checkCurrentStepAnswer(String keyWord);
    Optional<QuestStepJpaEntity> getNextQuestStepForCurrentUser();
    void finishQuestForCurrentUser();
}
