package org.skypro.counter_service.repository;

import org.skypro.counter_service.model.Question;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }


    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);

    }
}