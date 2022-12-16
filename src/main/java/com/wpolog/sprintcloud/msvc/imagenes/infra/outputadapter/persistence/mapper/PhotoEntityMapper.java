package com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence.mapper;

import com.wpolog.sprintcloud.msvc.imagenes.domain.Photo;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence.entity.PhotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PhotoEntityMapper {

    PhotoEntityMapper INSTANCE= Mappers.getMapper(PhotoEntityMapper.class);

    @Mapping( source = "photo.id", target="id")
    @Mapping( source = "photo.urlPhoto", target="urlPhoto")
    @Mapping( source = "photo.pathPhoto", target="pathPhoto")
    @Mapping( source = "photo.personId", target="personId")
    PhotoEntity photoToPhotoEntity(Photo photo);

    @Mapping( source = "photoEntity.id", target="id")
    @Mapping( source = "photoEntity.urlPhoto", target="urlPhoto")
    @Mapping( source = "photoEntity.pathPhoto", target="pathPhoto")
    @Mapping( source = "photoEntity.personId", target="personId")
    Photo photoEntityToPhoto(PhotoEntity photoEntity);

}
