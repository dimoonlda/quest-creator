package ua.kiev.dimoon.questcreator.user.service.impl.services;

import org.springframework.stereotype.Service;
import ua.kiev.dimoon.questcreator.base.service.impl.service.AbstractBaseService;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.UserJpaRepository;
import ua.kiev.dimoon.questcreator.user.service.api.services.UserService;

@Service
public class UserServiceImpl extends AbstractBaseService<UserJpaEntity, Integer, UserJpaRepository> implements UserService {

    @Override
    public UserJpaEntity save(UserJpaEntity userEntity) {
        UserJpaEntity newUser = new UserJpaEntity();
        if (null != userEntity.getId()) {
            newUser = repository.findOne(userEntity.getId());
        }
        newUser.setFirstName(userEntity.getFirstName());
        newUser.setUserPassword(userEntity.getUserPassword());
        newUser.setUserLogin(userEntity.getUserLogin());
        repository.save(newUser);
        return newUser;
    }
}
