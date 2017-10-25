package ua.kiev.dimoon.questcreator.common.dao.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserQuestJpaEntity;

import java.util.List;

public interface UserQuestJpaRepository extends JpaRepository<UserQuestJpaEntity, Integer> {
    List<UserQuestJpaEntity> findByUser_UserLogin(String userLogin);
    UserQuestJpaEntity findFirstByUser_UserLoginAndId(String userLogin, Integer id);
}
