package ua.kiev.dimoon.questcreator.user.service.api.services;

import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserJpaEntity;

import java.util.List;

public interface UserService {
    List<UserJpaEntity> getUsers();
}
