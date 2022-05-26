package database;

public class Progress {
    private int completion;
    private String lessonName;
    private Question[] questions = new Question[5];

    public Progress(){}

    public boolean isComplete(){
        if(completion >= 10){
            return true;
        }
        return false;
    }

    public Question getQuestion(String prompt){
        for(Question question: questions){
            if(question.getPrompt().equals(prompt)){
                return question;
            }
        }

        return null;
    }




}
