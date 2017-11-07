package ua.kiev.dimoon.questcreator.quest.service.impl.services;

import org.springframework.stereotype.Service;
import ua.kiev.dimoon.questcreator.base.service.impl.service.AbstractBaseService;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.QuestStepJpaRepository;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestStepService;

import java.util.List;

@Service
public class QuestStepServiceImpl
        extends AbstractBaseService<QuestStepJpaEntity, Integer, QuestStepJpaRepository>
        implements QuestStepService {

    @Override
    public List<QuestStepJpaEntity> findAllByQuestId(Integer questId) {
        return repository.findAllByQuest_IdOrderByOrder(questId);
    }
}
