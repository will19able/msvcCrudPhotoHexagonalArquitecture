package com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoEntity {

    @Id
    private String id;

    private String urlPhoto;

    private String pathPhoto;

    private Long personId;

}
