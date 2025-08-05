package org.skypro.counter_service.exeption;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException() {
        super("Сообщение для пользователя");
    }
}