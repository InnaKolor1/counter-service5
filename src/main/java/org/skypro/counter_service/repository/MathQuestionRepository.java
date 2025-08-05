package org.skypro.counter_service.repository;


import org.skypro.counter_service.model.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Repository
public class MathQuestionRepository implements QuestionRepository {

    private Set<Question> mathQuestions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        mathQuestions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(Question question) {
        mathQuestions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(mathQuestions);
    }

}