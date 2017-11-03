package ua.kiev.dimoon.questcreator.user.service.api.services;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserJpaEntity> getUsers();
    void delete(Integer userId);
    Optional<UserJpaEntity> findUserById(Integer userId);
}
