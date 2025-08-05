package org.skypro.counter_service.handler;

import org.skypro.counter_service.exeption.QuestionIllegalArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExaminerServiceImplHandler {

    @ExceptionHandler(QuestionIllegalArgumentException.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorUUID = logErrorToNoSql(e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Количество запрашиваемых вопросов превышает количества вопросов в хранилище " + e.getMessage());

    }

    private String logErrorToNoSql(Exception e) {
        return "";
    }
}
