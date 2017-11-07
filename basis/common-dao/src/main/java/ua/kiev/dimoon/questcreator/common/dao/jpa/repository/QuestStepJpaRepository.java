package ua.kiev.dimoon.questcreator.common.dao.jpa.repository;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;

import java.util.List;

public interface QuestStepJpaRepository extends BaseJpaRepository<QuestStepJpaEntity, Integer> {
    List<QuestStepJpaEntity> findAllByQuest_IdOrderByOrder(Integer questId);
}
