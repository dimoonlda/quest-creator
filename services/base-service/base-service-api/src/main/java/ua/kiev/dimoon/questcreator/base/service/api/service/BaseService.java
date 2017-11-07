package ua.kiev.dimoon.questcreator.base.service.api.service;

import ua.kiev.dimoon.questcreator.common.dao.jpa.repository.BaseJpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E, ID extends Serializable> {

    BaseJpaRepository<E, ID> getRepository();

    List<E> findAll();

    Optional<E> findOne(ID id);

    <S extends E> S save(S entity);

    void delete(E entity);

    void delete(ID id);

}
