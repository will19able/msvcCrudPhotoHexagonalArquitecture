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
public class ResponseHandler {


    public static final String MESSAGE_SUCCEFULY = "Successfuly";

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        return new ResponseEntity<>(map,status);
    }
}

