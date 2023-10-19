package com.roopepeli.pelikoodi;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizService {

    // List to store quiz questions
    private List<quizquestion> quizquestions;
    // Constructor to initialize QuizService with a list of quiz questions
    public QuizService(List<quizquestion> quizQuestions) {
        
        this.quizquestions = quizQuestions;
        
    }
    // Getter method to retrieve the list of all quiz questions
    public List<quizquestion> getQuizQuestions() {
        return quizquestions;
    }
    // Method to retrieve a specific quiz question by its ID
    public quizquestion getQuizQuestionById(int id) {
        for (quizquestion question : quizquestions) {
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }
    // Method to add a new quiz question to the list
    public boolean addQuizQuestion(quizquestion newQuestion) {
        // we asume ID's are unique
        return quizquestions.add(newQuestion);
    }
    //method that calculates the user score, it asumes that the question are answered in order
    public int calculateScore(List<String> userAnswers) {
        int score = 0;
        for (int i = 0; i < Math.min(quizquestions.size(), userAnswers.size()); i++) {
            quizquestion question = quizquestions.get(i);
            String userAnswer = userAnswers.get(i);

            if (question.checkAnswer(userAnswer)) {
                score++;
            }
        }

        return score;
    }
}