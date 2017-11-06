package ua.kiev.dimoon.questcreator.quest.service.api.services;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserQuestJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestService {
    List<UserQuestJpaEntity> getQuestsForCurrentUser();
    void startQuestForCurrentUser(int questId);
    Boolean checkCurrentStepAnswer(String keyWord);
    Optional<QuestStepJpaEntity> getCurrentQuestStepForCurrentUser();
    void finishQuestForCurrentUser();
    List<QuestJpaEntity> getQuests();
    void delete(Integer id);
    QuestJpaEntity save(QuestJpaEntity quest);
    Optional<QuestJpaEntity> findQuestById(Integer questId);

    static Optional<QuestJpaEntity> findCurrentQuest(Set<UserQuestJpaEntity> userQuests) {
        if (null == userQuests){
            return Optional.empty();
        }
        return userQuests
                .stream()
                .filter(userQuestEntity -> !userQuestEntity.getCompleted() && null != userQuestEntity.getQuestStep())
                .findFirst()
                .map(UserQuestJpaEntity::getQuest);
    }
}
