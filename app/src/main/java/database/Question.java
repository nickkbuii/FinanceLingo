package database;

import java.util.HashMap;

public class Question {
    String[] prompts;
    String[][] choices;
    String[] answers;

    public Question(String[] prompts, String[][] choices, String[] answers) {
        this.prompts = prompts;
        this.choices = choices;
        this.answers = answers;
    }

    public String[] getPrompts() {
        return prompts;
    }
    public void setPrompts(String[] prompts) {
        this.prompts = prompts;
    }
    public String[][] getChoices() {
        return choices;
    }
    public void setChoices(String[][] choices) {
        this.choices = choices;
    }
    public String[] getAnswers() {
        return answers;
    }
    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getChoice1(int choiceNum, int question){
        return choices[choiceNum][question];
    }
    
    public String getQuestion(int a) {
        String question = prompts[a];
        return question;
    }

    public String getCorrectAnswer(int a){
        String answer = answers[a];
        return answer;
    }
}
