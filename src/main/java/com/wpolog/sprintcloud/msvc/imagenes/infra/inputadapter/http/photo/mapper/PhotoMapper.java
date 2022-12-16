package com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.photo.mapper;

import com.wpolog.sprintcloud.msvc.imagenes.domain.Photo;
import com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.photo.dto.UploadUpdatePhotoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MapperHelper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PhotoMapper {

    PhotoMapper INSTANCE= Mappers.getMapper(PhotoMapper.class);


    @Mapping( source = "photo.id", target="id")
    @Mapping( source = "photo.urlPhoto", target="urlFoto")
    @Mapping( source = "photo.pathPhoto", target="pathFoto")
    @Mapping( source = "photo.personId", target="personaId")
    UploadUpdatePhotoResponse photoToUploadUpdatePhotoResponse(Photo photo);



}
