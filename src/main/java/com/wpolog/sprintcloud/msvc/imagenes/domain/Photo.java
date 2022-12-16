package com.wpolog.sprintcloud.msvc.imagenes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    private String id;

    private String urlPhoto;

    private String pathPhoto;

    private Long personId;
}
