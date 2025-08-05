package org.skypro.counter_service.controller;

import org.skypro.counter_service.model.Question;
import org.skypro.counter_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.skypro.counter_service.controller.JavaQuestionController;
import org.skypro.counter_service.repository.QuestionRepository;
import java.util.Collection;

@RestController
@RequestMapping("/exam")

public class JavaQuestionController {

    private final QuestionService service;

    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService service) {
        this.service = service;
    }

    @GetMapping("/java/add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/java/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer) {
        Question newQuestion = new Question(question, answer);
        return service.remove(newQuestion);
    }

    @GetMapping("/java")
    public Collection<Question> getQuestions() {
        return service.getAll();
    }
}