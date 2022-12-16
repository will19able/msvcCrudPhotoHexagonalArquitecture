package com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.photo.mapper;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class MapperHelper {

    @Named("stringCapitalize")
    public String stringCapitalize(String value) {
        return  String.join(" ",
                        Arrays.stream(value.split(" "))
                                .filter(StringUtils::isNotBlank)
                                .map( word -> StringUtils.capitalize(word.toLowerCase())).collect(Collectors.toList()))
                .replace(" ,", ",")
                .replace(" ;", ";")
                .replace(" .", ".");
    }

}
