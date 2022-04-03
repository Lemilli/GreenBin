package com.example.misterbin.model;

public class QuestionData {
    public int level;
    public String question;
    public String option1;
    public String option2;
    public String option3;
    public String correct_answer;

    // Class Constructor
    public QuestionData(int level, String question, String option1, String option2, String option3, String correct_answer){
        this.level = level;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correct_answer = correct_answer;
    }
}

