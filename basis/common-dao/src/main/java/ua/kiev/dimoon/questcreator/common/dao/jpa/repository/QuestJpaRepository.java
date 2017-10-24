package ua.kiev.dimoon.questcreator.common.dao.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.QuestJpaEntity;

public interface QuestJpaRepository extends JpaRepository<QuestJpaEntity, Integer> {
}
