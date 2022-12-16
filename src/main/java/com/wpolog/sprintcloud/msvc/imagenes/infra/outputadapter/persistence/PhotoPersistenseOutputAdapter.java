package com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence;

import com.wpolog.sprintcloud.msvc.imagenes.domain.Photo;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence.entity.PhotoEntity;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence.mapper.PhotoEntityMapper;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence.repository.PhotoRepository;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputport.persistense.PhotoPersistenseOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PhotoPersistenseOutputAdapter implements PhotoPersistenseOutputPort {

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public Photo save(Photo photo) {
        PhotoEntity photoEntity = photoRepository.save(PhotoEntityMapper.INSTANCE.photoToPhotoEntity(photo));
        return PhotoEntityMapper.INSTANCE.photoEntityToPhoto(photoEntity);
    }

    @Override
    public Optional<Photo> findById(String id) {
        Optional<PhotoEntity> photoEntityOptional = photoRepository.findById(id);
        if (photoEntityOptional.isPresent()){
            return Optional.of(PhotoEntityMapper.INSTANCE.photoEntityToPhoto(photoEntityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Photo> findByIdPerson(Long idPerson) {
        Optional<PhotoEntity> photoEntityOptional = photoRepository.findByPersonId(idPerson);
        if (photoEntityOptional.isPresent()){
            return Optional.of(PhotoEntityMapper.INSTANCE.photoEntityToPhoto(photoEntityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteByPersonId(Long personId) {
        photoRepository.deleteByPersonId(personId);
    }
}
