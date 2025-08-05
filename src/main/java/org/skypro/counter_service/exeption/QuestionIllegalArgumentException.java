package org.skypro.counter_service.exeption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionIllegalArgumentException extends RuntimeException {

    public QuestionIllegalArgumentException() {
        super("Сообщение");
    }
}