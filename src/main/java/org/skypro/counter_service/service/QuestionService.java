package org.skypro.counter_service.service;

import org.skypro.counter_service.model.Question;

import java.util.Collection;

public interface QuestionService {

    public Question add(String question, String answer);

    public Question remove(Question question);

    public Collection<Question> getAll();
    public Question getRandomQuestion();


}