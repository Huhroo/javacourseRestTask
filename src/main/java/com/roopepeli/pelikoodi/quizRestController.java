package com.roopepeli.pelikoodi;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class quizRestController {
    
   
    private final QuizService quizService;
    
    public quizRestController(QuizService quizService) {
        this.quizService = quizService;
    }
    //this mapping is my info page with info
    @GetMapping("/")
    public String homepage() {
        return "Welcome to the quiz, You should first post some question using Postman or something like it. I have included ready made "
         +"postman collection to githubpage. so it is eesier to test. Game follows simple steps on the path /quizgame it displays all the questions and answers "
         +"then on postman you can post /submit your answers in the body as raw JSON, but remember answers are case sensitive. Game then displays all the points"
         +"you get from your answers (max 1point per right answer)"
         +"question body JSON should be something like {\r\n" + 
                 "  \"id\": 2,\r\n" + 
                 "  \"question\": \"What is the capital of France?\",\r\n" + 
                 "  \"options\": [\"Berlin\", \"London\", \"Paris\"],\r\n" + 
                 "  \"answer\": \"Paris\"\r\n" + 
                 "} and answer's [\r\n" + //
                         "  \"5\",\r\n" + //
                         "  \"Paris\"\r\n" + //
                         "]" ;
    }

    //This mapping shows you a question if you provide it with existing id
        @GetMapping("/question/{id}")
    public ResponseEntity<quizquestion> getQuizQuestionById(@PathVariable int id) {
        quizquestion question = quizService.getQuizQuestionById(id);

        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //this mapping is used to create new questions
    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody quizquestion newQuestion) {
        boolean added = quizService.addQuizQuestion(newQuestion);

        if (added) {
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //this mapping displays all quizquestion objects and their contents
    @GetMapping("/questions")
    public List<quizquestion> getQuizQuestions() {
        return quizService.getQuizQuestions();
    }
    //this mapping is used to submit the
    @PostMapping("/submit")
    public String submitQuiz(@RequestBody List<String> userAnswers) {
        int score = quizService.calculateScore(userAnswers);
        return "Quiz submitted. Your score: " + score;
    }
    //this mapping displays all the questions but hides the answers
    @GetMapping("/quizgame")
    public List<quizquestion> getQuizGame() {
        List<quizquestion> quizQuestions = quizService.getQuizQuestions();

        // Map quizquestions to hide answers
        return quizQuestions.stream()
                .map(q -> new quizquestion(q.getId(), q.getQuestion(), q.getOptions(), null))
                .collect(Collectors.toList());
    }

}
