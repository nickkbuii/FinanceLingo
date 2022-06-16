package database;

public class Question {
    String[] prompts;
    String[][] choices;
    String[] correctAnswers;

    public Question(String[] prompts, String[][] choices, String[] correctAnswers) {
        this.prompts = prompts;
        this.choices = choices;
        this.correctAnswers = correctAnswers;
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
        return correctAnswers;
    }

    public void setAnswers(String[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getChoice(int question, int choiceNum){
        return choices[question][choiceNum];
    }
    
    public String getQuestion(int a) {
        String question = prompts[a];
        return question;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswers[a];
        return answer;
    }

    public static void sayHi(){
        //HI
    }
}
