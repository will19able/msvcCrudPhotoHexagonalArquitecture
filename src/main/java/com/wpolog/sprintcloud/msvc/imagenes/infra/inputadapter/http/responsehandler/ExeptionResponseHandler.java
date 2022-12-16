package com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.responsehandler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExeptionResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, String codeError) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
        map.put("message", message);
        map.put("status", status.value());
        map.put("codeError", codeError);
        return new ResponseEntity<>(map,status);
    }
}

