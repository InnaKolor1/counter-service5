package org.skypro.counter_service.repository;

import org.skypro.counter_service.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    public Question add(String question, String answer);

    public Question remove(Question question);

    public Collection<Question> getAll();

}