package ua.kiev.dimoon.questcreator.user.service.impl.services;

import org.springframework.stereotype.Service;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserJpaEntity;
import ua.kiev.dimoon.questcreator.user.service.api.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserJpaEntity> getUsers() {
        return null;
    }
}
