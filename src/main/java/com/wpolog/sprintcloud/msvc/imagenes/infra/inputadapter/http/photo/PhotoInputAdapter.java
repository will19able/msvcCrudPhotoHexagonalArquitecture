package com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.photo;

import com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.photo.dto.UploadUpdatePhotoRequest;
import com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.photo.mapper.PhotoMapper;
import com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.responsehandler.ResponseHandler;
import com.wpolog.sprintcloud.msvc.imagenes.infra.inputport.PhotoInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/photo")
public class PhotoInputAdapter {

    @Autowired
    PhotoInputPort photoInputPort;

    @PostMapping(value = "upload", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadPhoto(@Valid @ModelAttribute UploadUpdatePhotoRequest uploadUpdatePhotoRequest){
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCEFULY, HttpStatus.OK,
                PhotoMapper.INSTANCE.photoToUploadUpdatePhotoResponse( photoInputPort
                        .createPhoto(uploadUpdatePhotoRequest.getIdPersona(), uploadUpdatePhotoRequest.getFile())));
    }

    @GetMapping(value = "get", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPhoto(@RequestParam String id ){
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCEFULY, HttpStatus.OK,
                PhotoMapper.INSTANCE.photoToUploadUpdatePhotoResponse( photoInputPort
                        .findById(id)));
    }

    @GetMapping(value = "getbyperson-id", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPhotoByPersonId(@RequestParam Long personaId ){
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCEFULY, HttpStatus.OK,
                PhotoMapper.INSTANCE.photoToUploadUpdatePhotoResponse( photoInputPort
                        .findByPersonId(personaId)));
    }

    @PutMapping(value = "update", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePhoto(@Valid @ModelAttribute UploadUpdatePhotoRequest uploadUpdatePhotoRequest){
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCEFULY, HttpStatus.OK,
                PhotoMapper.INSTANCE.photoToUploadUpdatePhotoResponse( photoInputPort
                        .updatePhoto(uploadUpdatePhotoRequest.getIdPersona(), uploadUpdatePhotoRequest.getFile())));
    }

    @DeleteMapping(value = "delete", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePhoto(@RequestParam Long personaId){
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCEFULY, HttpStatus.OK,
                PhotoMapper.INSTANCE.photoToUploadUpdatePhotoResponse( photoInputPort
                        .deletePhoto(personaId)));
    }

}
