package database;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class User {

    //initializing username, pw, email, id variables
    private String username;
    private String password;
    private String email;
    private String id;

    //Budgeting scores
    private int qNum;
    private int qScore;

    private HashMap<String, Integer> score;
    private HashMap<String, Integer> question;

    //defines username, email, id, password
    public User(String username, String email, String id) {
        this.username = username;
        this.email = email;
        this.id = id;

        score  = new HashMap<>();
        score.put("Budgeting", 0);
        score.put("Debt", 0);

        question = new HashMap<>();
        question.put("Budgeting", 0);
        question.put("Debt", 0);
    }
    public User(String username, String password, String email, String id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;

        score  = new HashMap<>();
        score.put("Budgeting", 0);
        score.put("Debt", 0);

        question = new HashMap<>();
        question.put("Budgeting", 0);
        question.put("Debt", 0);
    }
    public User() {
        this.username = "";
        this.password = "";
        this.email = "";

        score  = new HashMap<>();
        score.put("Budgeting", 0);
        score.put("Debt", 0);

        question = new HashMap<>();
        question.put("Budgeting", 0);
        question.put("Debt", 0);
    }

    //Getter
    public String getUsername() {
        return username;
    }

    //public String getPw(){return password;};
    public String getId(){return id;};
    public String getEmail(){return email;}

    //Setter
    public void setUsername(String name) {
        this.username = name;
    }
    public void setId(String id){this.id = id;}
    public void setPw(String pw){this.password = pw;}
    public void setEmail(String email){this.email = email;}

    public void setQNum(String lesson, int value){
        this.question.remove(lesson);
        this.question.put(lesson, value);
    }

    public void setQScore(String lesson, int value){
        this.score.remove(lesson);
        this.score.put(lesson, value);
    }

    public void incrementQScore(){
        this.qScore+=1;
        System.out.println(qScore);
    }

    //methods to return debt_question number, score, pw
    public String pw(){
        return password;
    }

    public int getQNum(String lesson){
        if(question.containsKey(lesson)){
            return (int)question.get(lesson);
        }
        else{
            Log.d("INFO", "NOT KEY");
            return 0;
        }

    }

    public int getQScore(String lesson){
        if(score.containsKey(lesson)){
            return (int)score.get(lesson);
        }
        else{
            Log.d("INFO", "NOT KEY");
            return 0;
        }

    }

    //changes username, pw to proper format
    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                "password='" + password + '\'' + '}';
    }

}
