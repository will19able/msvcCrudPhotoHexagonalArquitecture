package com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence.repository;

import com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence.entity.PhotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository  extends MongoRepository<PhotoEntity, String> {

    Optional<PhotoEntity> findByPersonId(Long personId);

    void deleteByPersonId(Long personId);
}
