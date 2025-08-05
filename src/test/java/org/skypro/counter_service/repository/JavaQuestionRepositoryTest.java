package org.skypro.counter_service.repository;

import org.skypro.counter_service.model.Question;
import org.skypro.counter_service.repository.JavaQuestionRepository;
import org.skypro.counter_service.repository.QuestionRepository;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaQuestionRepositoryTest {

    private final QuestionRepository questionRepository = new JavaQuestionRepository();

    @BeforeEach
    public void beforeEach() {
        questionRepository.add("Java вопрос 1", "Java ответ 1");
        questionRepository.add("Java вопрос 2", "Java ответ 2");
        questionRepository.add("Java вопрос 3", "Java ответ 3");
    }

    @AfterEach
    public void afterEach() {
        questionRepository.getAll().stream()
                .map(e -> questionRepository.remove(e));
    }

    @Test
    public void addTest() {
        int beforeCount = questionRepository.getAll().size();
        Question expected = new Question("Java вопрос 4", "Java ответ 4");
        Assertions.assertThat(questionRepository.add("Java вопрос 4", "Java ответ 4")).isEqualTo(expected)
                .isIn(questionRepository.getAll());
        Assertions.assertThat(questionRepository.getAll()).hasSize(beforeCount + 1);

    }

    @Test
    public void removeTest() {
        int beforeCount = questionRepository.getAll().size();
        Question expected = new Question("Java вопрос 1", "Java ответ 1");
        Assertions.assertThat(questionRepository.remove(new Question("Java вопрос 1", "Java ответ 1"))).isEqualTo(expected)
                .isNotIn(questionRepository.getAll());
        Assertions.assertThat(questionRepository.getAll()).hasSize(beforeCount - 1);
    }


    @Test
    public void getAllTest() {
        Assertions.assertThat(questionRepository.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Java вопрос 1", "Java ответ 1"),
                        new Question("Java вопрос 2", "Java ответ 2"),
                        new Question("Java вопрос 3", "Java ответ 3")
                );
    }

}