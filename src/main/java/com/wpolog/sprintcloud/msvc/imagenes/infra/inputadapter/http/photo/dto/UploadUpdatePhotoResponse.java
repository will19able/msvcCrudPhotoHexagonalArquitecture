package com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.photo.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UploadUpdatePhotoResponse {

    public String id;
    public String urlFoto;
    public String pathFoto;
    public Long personaId;
}
