package com.wpolog.sprintcloud.msvc.imagenes.infra.inputport;

import com.wpolog.sprintcloud.msvc.imagenes.domain.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoInputPort {

    public Photo createPhoto(Long personId, MultipartFile file);

    public Photo updatePhoto(Long personId, MultipartFile file);

    public Photo findById(String id);
    public Photo findByPersonId(Long personId);

    public Photo deletePhoto(Long personId);
}
