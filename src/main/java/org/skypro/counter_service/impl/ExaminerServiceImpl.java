package org.skypro.counter_service.impl;

import org.skypro.counter_service.exeption.QuestionIllegalArgumentException;
import org.skypro.counter_service.model.Question;
import org.skypro.counter_service.service.ExaminerService;
import org.skypro.counter_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    private final Random random = new Random();
    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (javaQuestionService.getAll().size() + mathQuestionService.getAll().size() < amount) {
            throw new QuestionIllegalArgumentException();
        }
        Set<Question> randomQuestions = new HashSet<>();
        Random random = new Random();
        while (randomQuestions.size() < amount) {
            int questionRandom = random.nextInt(2);
            switch (questionRandom) {
                case 0:
                    randomQuestions.add(javaQuestionService.getRandomQuestion());
                    break;
                case 1:
                    randomQuestions.add(mathQuestionService.getRandomQuestion());
                    break;
            }
        }
        return randomQuestions;
    }
}