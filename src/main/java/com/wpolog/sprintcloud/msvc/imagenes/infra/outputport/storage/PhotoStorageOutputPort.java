package com.wpolog.sprintcloud.msvc.imagenes.infra.outputport.storage;

import com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.storage.awss3.dto.Asset;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorageOutputPort {

    public String createPhoto(MultipartFile file);

    //public String updatePhoto(MultipartFile file);

    public Asset getFile(String key);

    public String getFileUrl(String key);

    public void deleteObject(String key);



}
