package com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.photo.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UploadUpdatePhotoRequest {

    @NotNull(message = "El id de la persona es obligatorio.")
    Long idPersona;

    @NotNull(message = "El archivo o imagen es obligatorio.")
    MultipartFile file;
}
