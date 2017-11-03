package ua.kiev.dimoon.questcreator.user.service.impl.services;

import org.springframework.stereotype.Service;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.UserJpaRepository;
import ua.kiev.dimoon.questcreator.user.service.api.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserJpaRepository userRepository;

    public UserServiceImpl(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserJpaEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Integer userId) {
        userRepository.delete(userId);
    }

    @Override
    public UserJpaEntity save(UserJpaEntity userEntity) {
        UserJpaEntity newUser = new UserJpaEntity();
        if (null != userEntity.getId()) {
            newUser = userRepository.findOne(userEntity.getId());
        }
        newUser.setFirstName(userEntity.getFirstName());
        newUser.setUserPassword(userEntity.getUserPassword());
        newUser.setUserLogin(userEntity.getUserLogin());
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public Optional<UserJpaEntity> findUserById(Integer userId) {
        return userRepository.findById(userId);
    }
}
