package ua.kiev.dimoon.questcreator.user.service.impl.services;

import org.springframework.stereotype.Service;
import ua.kiev.dimoon.questcreator.common.dao.jpa.entity.UserJpaEntity;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.UserJpaRepository;
import ua.kiev.dimoon.questcreator.user.service.api.services.UserService;

import java.util.List;

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
}
