package com.wpolog.sprintcloud.msvc.imagenes.infra.outputport.persistense;

import com.wpolog.sprintcloud.msvc.imagenes.domain.Photo;

import java.util.Optional;

public interface PhotoPersistenseOutputPort {

    public Photo save(Photo photo);

    public Optional<Photo> findById(String id);

    public Optional<Photo> findByIdPerson(Long idPerson);

    public void deleteByPersonId( Long personId );
}
