package org.skypro.counter_service.service;

import org.skypro.counter_service.model.Question;

import java.util.Collection;

public interface ExaminerService {

    public Collection<Question> getQuestions(int amount);
}