package com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.storage.awss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class Asset {

    private byte[] content;
    private String contenType;
}
