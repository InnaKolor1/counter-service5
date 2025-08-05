package org.skypro.counter_service.service;

import org.skypro.counter_service.exeption.QuestionNotFoundException;
import org.skypro.counter_service.model.Question;
import org.skypro.counter_service.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.counter_service.impl.JavaQuestionService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {


    @Mock
    private QuestionRepository repository;

    @InjectMocks
    private JavaQuestionService questionService;

    private Set<Question> questions;

    private Question question1;

    private Question question2;

    private Question question3;

    private Question question4;

    @BeforeEach
    public void beforeEach() {
        question1 = new Question("Java вопрос 1", "Java ответ 1");
        question2 = new Question("Java вопрос 2", "Java ответ 2");
        question3 = new Question("Java вопрос 3", "Java ответ 3");
        question4 = new Question("Java вопрос 4", "Java ответ 4");

        questions = new HashSet<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
    }


    @Test
    public void addTest() {
        when(repository.add(question4.getQuestion(), question4.getAnswer())).thenReturn(question4);
        Assertions.assertThat(questionService.add(question4.getQuestion(), question4.getAnswer())).isEqualTo(question4);
    }

    @Test
    public void removeTest() {
        when(repository.getAll()).thenReturn(questions);
        when(repository.remove(question1)).thenReturn(question1);
        Assertions.assertThat(questionService.remove(question1)).isEqualTo(question1);
    }

    @Test
    public void removeWhenNotFoundTest() {
        when(repository.getAll()).thenReturn(questions);
        Assertions.assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(question4));
    }

    @Test
    public void getAllTest() {
        when(repository.getAll()).thenReturn(questions);
        Assertions.assertThat(questionService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Java вопрос 1", "Java ответ 1"),
                        new Question("Java вопрос 2", "Java ответ 2"),
                        new Question("Java вопрос 3", "Java ответ 3")
                );
        try {

            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.err.println("Ошибка: деление на ноль!");
        }
    }
}