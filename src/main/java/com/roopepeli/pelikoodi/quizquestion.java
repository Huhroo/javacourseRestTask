package com.roopepeli.pelikoodi;

import java.util.List;

public class quizquestion {
    private int id;
    private String question;
    private List<String> options;
    private String answer;
    


    public quizquestion() {
    }

    public quizquestion(int id, String question, List<String> options, String answer) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public boolean checkAnswer(String userInput){
        return answer.equals(userInput);
    }
}
