package database;

public class Question {

    //initialize prompts, answer options and correct answers that were extended from Budgeting.java
    String[] prompts;
    String[][] choices;
    String[] correctAnswers;

    //define prompts, choices and correct Answers for Question.java
    public Question(String[] prompts, String[][] choices, String[] correctAnswers) {
        this.prompts = prompts;
        this.choices = choices;
        this.correctAnswers = correctAnswers;
    }

    //retrieve and initialize prompts
    public String[] getPrompts() {
        return prompts;
    }
    public void setPrompts(String[] prompts) {
        this.prompts = prompts;
    }

    //retrieve and initialize answer options
    public String[][] getChoices() {
        return choices;
    }
    public void setChoices(String[][] choices) {
        this.choices = choices;
    }

    //retrieve and initialize correct answers
    public String[] getAnswers() {
        return correctAnswers;
    }
    public void setAnswers(String[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    //retrieve answer option chosen user from Budgeting.java
    public String getChoice(int question, int choiceNum){
        return choices[question][choiceNum];
    }

    //retrieve current question of user from Budgeting.java
    public String getQuestion(int a) {
        String question = prompts[a];
        return question;
    }

    //retrieve correct answer to current question of user from Budgeting.java
    public String getCorrectAnswer(int a){
        String answer = correctAnswers[a];
        return answer;
    }
}
