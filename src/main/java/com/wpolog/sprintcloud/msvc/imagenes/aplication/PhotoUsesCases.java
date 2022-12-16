package com.wpolog.sprintcloud.msvc.imagenes.aplication;

import com.wpolog.sprintcloud.msvc.imagenes.domain.Photo;
import com.wpolog.sprintcloud.msvc.imagenes.domain.exeption.BusinessException;
import com.wpolog.sprintcloud.msvc.imagenes.domain.exeption.CodeError;
import com.wpolog.sprintcloud.msvc.imagenes.infra.inputport.PhotoInputPort;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputport.persistense.PhotoPersistenseOutputPort;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputport.storage.PhotoStorageOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class PhotoUsesCases implements PhotoInputPort {

    @Autowired
    PhotoPersistenseOutputPort photoPersistenseOutputPort;

    @Autowired
    PhotoStorageOutputPort photoStorageOutputPort;

    @Override
    public Photo createPhoto(Long personId, MultipartFile file) {
        Optional<Photo> foundPhotoOptional = photoPersistenseOutputPort.findByIdPerson(personId);
        validateExistPhoto(foundPhotoOptional);
        String  pathUrl = photoStorageOutputPort.createPhoto(file);
       return photoPersistenseOutputPort.save(Photo.builder()
                        .urlPhoto(photoStorageOutputPort.getFileUrl(pathUrl))
                        .pathPhoto(pathUrl)
                        .personId(personId)
                .build());

    }

    @Override
    public Photo updatePhoto(Long personId, MultipartFile file) {

        Optional<Photo> foundPhotoOptional = photoPersistenseOutputPort.findByIdPerson(personId);
        validateDontExistPhoto(foundPhotoOptional);
        if (foundPhotoOptional.isPresent()){
            photoStorageOutputPort.deleteObject(foundPhotoOptional.get().getPathPhoto());
            String  pathUrl = photoStorageOutputPort.createPhoto(file);
            foundPhotoOptional.get().setPathPhoto(pathUrl);
            foundPhotoOptional.get().setUrlPhoto(photoStorageOutputPort.getFileUrl(pathUrl));
           return photoPersistenseOutputPort.save(foundPhotoOptional.get());
        }
        return null;
    }

    @Override
    public Photo findById(String id) {
        Optional<Photo> photoOptional = photoPersistenseOutputPort.findById(id);
        return photoOptional.isPresent() ? photoOptional.get() : null;
    }

    @Override
    public Photo findByPersonId(Long personId) {
        Optional<Photo> photoOptional = photoPersistenseOutputPort.findByIdPerson(personId);
        return photoOptional.isPresent() ? photoOptional.get() : null;
    }

    @Override
    public Photo deletePhoto(Long personId) {
        Optional<Photo> foundPhotoOptional = photoPersistenseOutputPort.findByIdPerson(personId);
        if (!foundPhotoOptional.isPresent()){
            return null;
        }
        photoStorageOutputPort.deleteObject(foundPhotoOptional.get().getPathPhoto());
        photoPersistenseOutputPort.deleteByPersonId(personId);
        return foundPhotoOptional.isPresent() ? foundPhotoOptional.get() : null;
    }

    private void validateExistPhoto(Optional<Photo> photo) {
        if (photo.isPresent()){
            throw new BusinessException(CodeError.REGISTERED_PHOTO.getDescription(), CodeError.REGISTERED_PHOTO.getId());
        }
    }

    private void validateDontExistPhoto(Optional<Photo> photo) {
        if (!photo.isPresent()){
            throw new BusinessException(CodeError.DONT_REGISTERED_PHOTO.getDescription(), CodeError.DONT_REGISTERED_PHOTO.getId());
        }
    }

}
