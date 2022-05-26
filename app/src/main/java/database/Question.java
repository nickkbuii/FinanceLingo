package database;

import java.util.HashMap;

public class Question {
    private String prompt;
    private HashMap<String, Boolean> answers;

    public Question(String prompt, HashMap<String, Boolean> map){
        this.prompt = prompt;
        this.answers = map;
    }

    public boolean check(String answer){
        if(answers.containsKey(answer)){
            return answers.get(answer);
        }
        return false;
    }

    public String getPrompt(){
        return prompt;
    }

}
