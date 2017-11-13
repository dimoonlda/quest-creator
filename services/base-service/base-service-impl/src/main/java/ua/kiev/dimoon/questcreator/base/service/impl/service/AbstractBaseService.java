package ua.kiev.dimoon.questcreator.base.service.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kiev.dimoon.questcreator.base.service.api.service.BaseService;
import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.BaseJpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseService<E, ID extends Serializable, R extends BaseJpaRepository<E, ID>> implements BaseService<E, ID> {

    @Autowired
    protected R repository;

    @Override
    public R getRepository() {
        return repository;
    }

    @Override
    public Optional<E> findOne(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public <S extends E> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    public E findById(ID id) {
        return repository.findOne(id);
    }
}
