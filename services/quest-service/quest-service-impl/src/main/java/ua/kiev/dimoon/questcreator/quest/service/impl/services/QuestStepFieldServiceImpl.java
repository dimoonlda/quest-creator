package ua.kiev.dimoon.questcreator.quest.service.impl.services;

import org.springframework.stereotype.Service;
import ua.kiev.dimoon.questcreator.base.service.impl.service.AbstractBaseService;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestStepFieldJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.QuestStepFieldJpaRepository;
import ua.kiev.dimoon.questcreator.quest.service.api.services.QuestStepFieldService;

@Service
public class QuestStepFieldServiceImpl
        extends AbstractBaseService<QuestStepFieldJpaEntity, Integer, QuestStepFieldJpaRepository>
        implements QuestStepFieldService {
}
