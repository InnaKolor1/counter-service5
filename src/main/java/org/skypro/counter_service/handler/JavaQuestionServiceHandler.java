package org.skypro.counter_service.handler;

import org.skypro.counter_service.exeption.QuestionNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JavaQuestionServiceHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public String removeWhenQuestionNotFoundException() {
        return "Вопрос не был найден";
    }
}