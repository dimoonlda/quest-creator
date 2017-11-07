package ua.kiev.dimoon.questcreator.common.dao.jpa.repository;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserQuestJpaEntity;

import java.util.List;

public interface UserQuestJpaRepository extends BaseJpaRepository<UserQuestJpaEntity, Integer> {
    List<UserQuestJpaEntity> findByUser_UserLoginAndCompleted(String userLogin, Boolean completed);
    UserQuestJpaEntity findFirstByUser_UserLoginAndQuest_Id(String userLogin, Integer id);
}
