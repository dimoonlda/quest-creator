package ua.kiev.dimoon.questcreator.quest.service.api.services;

import ua.kiev.dimoon.questcreator.base.service.api.service.BaseService;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;

import java.util.List;

public interface QuestStepService extends BaseService<QuestStepJpaEntity, Integer>{
    List<QuestStepJpaEntity> findAllByQuestId(Integer questId);
}
