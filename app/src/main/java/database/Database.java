package database;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.financelingo.financelingo.AccSettings;
import com.financelingo.financelingo.Lessons;
import com.financelingo.financelingo.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

import Global.Global;

public class Database{

    //initializing firebase variables
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private FirebaseUser user;

    public Database(){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
    }

    public FirebaseAuth getAuth(){
        return fAuth;
    }

    public FirebaseFirestore getFirestore(){
        return fStore;
    }

    public FirebaseUser getUser(){
        return user;
    }

    public boolean createAcc(Context context, String username, String email, String password){
        final boolean[] val = {true};
        //SIGNS CURRENT USER OUT
        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }

        //Creates new User onto firebase authentication
        fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //CREATES GLOBAL.USER OBJECT
                            Global.user = new User(username, password, email, fAuth.getCurrentUser().getUid());


                            //Adds to profile firebase firestore
                            fStore.collection("User")
                                    .document(fAuth.getCurrentUser().getUid() + " + " + Global.user.getEmail())
                                    .set(Global.user);

                            //Adds to email-username connection
                            HashMap<String, String> emailMap = new HashMap<>();
                            emailMap.put("Email", Global.user.getEmail());

                            //Adds to allow login with username
                            fStore.collection("Emails")
                                    .document(Global.user.getUsername())
                                    .set(emailMap);

                            //Adds to email-username connection
                            HashMap<String, Integer> budgScore = new HashMap<>();
                            //Question and score
                            budgScore.put("Score", Integer.valueOf(0));
                            //Adds to allow login with username
                            fStore.collection("Budgeting")
                                    .document(Global.user.getUsername())
                                    .set(budgScore);

                            HashMap<String, Integer> debtScore = new HashMap<>();
                            debtScore.put("Score", Integer.valueOf(0));
                            fStore.collection("Debt")
                                    .document(Global.user.getUsername())
                                    .set(budgScore);

                            HashMap<String, Integer> taxScore = new HashMap<>();
                            taxScore.put("Score", Integer.valueOf(0));
                            fStore.collection("Taxes")
                                    .document(Global.user.getUsername())
                                    .set(taxScore);

                            HashMap<String, Integer> investScore = new HashMap<>();
                            investScore.put("Score", Integer.valueOf(0));
                            fStore.collection("Investments")
                                    .document(Global.user.getUsername())
                                    .set(investScore);

                            //SETS USERNAME TO AUTHENTICATION USERNAME
                            UserProfileChangeRequest profReq = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username)
                                    .build();


                            fAuth.getCurrentUser().updateProfile(profReq).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Log.d("INFO", fAuth.getCurrentUser().getDisplayName().toString());
                                    }
                                }
                            });
                            Toast.makeText(context, "ACCOUNT CREATED", Toast.LENGTH_SHORT).show();
                            val[0] = true;
                        }
                        else{
                            Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show();
                            val[0] = false;
                        }
                    }
                });
        Toast.makeText(context, String.valueOf(val[0]), Toast.LENGTH_SHORT).show();
        return val[0];
    }

    public void login(Context context, String username, String password){
        //TEMPORARY : AUTO LOGOUT -> ASK USER IF THEY WISH TO LOG OUT

        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }

        //PROBLEM
        // RUNNING OUTSIDE LINES BEFORE INSIDE
        // CALLING SIGN IN WITH PW AFTER RETURNING
        if(username.contains("@")){
            //LOGS IN  USING EMAIL
            fAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //LOADS DATA FOR GLOBAL.USER

                        //SETS PASSWORD
                        Global.user.setPw(password);

                    }
                    else{
                        Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            //CHECKS EMAILS FIRESTORE FOR EMAIL LINKED TO USERNAME
            fStore.collection("Emails").document(username).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot doc) {
                    if(doc.exists()){
                        String email = doc.getString("Email");

                        //LOGINS INTO AUTHENTICATION USING EMAIL FROM FIRESTORE
                        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.d("USER INFO", fAuth.getCurrentUser().getUid() + " + " + fAuth.getCurrentUser().getEmail());
                                    //LOADS DATA NEEDED FOR GLOBAL.USER

                                    //SETS PASSWORD FROM FIREBASE
                                    Global.user.setPw(password);

                                }
                                else{
                                    Toast.makeText(context, "LOGIN FAIL", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(context, "DID NOT WORK", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void updateEmail(Context context, String newEmail){
        //CHANGES EMAIL FOR FIREBASE AUTHENTICATION
        user.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //DELETES CURRENT USER OBJECT IN FIRESTORE DATABASE
                    fStore.collection("User").document(user.getUid() + " + " + Global.user.getEmail()).delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        //CHANGES EMAIL ON USER OBJECT
                                        Global.user.setEmail(newEmail);//SET TO NEW EMAIL

                                        Log.d("INFO", user.getUid() + " + " + Global.user.getEmail());

                                        //CREATES NEW DOCUMENT USING ATTRIBUTES FROM OLD ONE
                                        HashMap<String, String> newMap = new HashMap<>();

                                        //INPUTS UPDATED ATTRIBUTES
                                        newMap.put("email", Global.user.getEmail()); // CHANGE TO USER INPUT EMAIL (NEW EMAIL)
                                        newMap.put("id", Global.user.getId());
                                        newMap.put("username", Global.user.getUsername());

                                        //CREATES DOCUMENT
                                        fStore.collection("User")
                                                .document(user.getUid() + " + " + Global.user.getEmail())
                                                .set(newMap);

                                        fStore.collection("Emails").document(Global.user.getUsername()).update("Email", Global.user.getEmail());
                                        Toast.makeText(context, "UPDATED", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

//    public void updatePw(Context context, String newPw){
//        fAuth.
//    }

    public boolean loadData(Context context){
        //GRABS DATA FROM FIRESTORE DATABASE
        String getData = fAuth.getCurrentUser().getUid().toString() + " + " + fAuth.getCurrentUser().getEmail();
        Log.d("INFO", getData);
        fStore.collection("User").document(getData).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                if(doc.exists()){
                    //SETS NECESSARY DATA INTO VARIABLES FOR USER OBJECT
                    String user = doc.getString("username");//Change to "Username"
                    String email = doc.getString("email");
                    String id = doc.getString("id");

                    //CREATES USER OBJECT
                    Global.user = new User(user, email, id);

                    Log.d("VALUE", "LOAD DATA :: WORKED");
                    //Lessons.setUser(Global.user.getUsername().toUpperCase());
                }
                else{
                    Toast.makeText(context, "DID NOT WORK :: LOAD DATA", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //SETS NEEDED SCORE VARIABLES FOR USER
        fStore.collection(Global.BUDGETING).document(fAuth.getCurrentUser().getDisplayName().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                if (doc.exists()) {
                    Global.user.setQScore(Global.BUDGETING, Integer.parseInt(doc.getLong("Score").toString()));
                    Global.user.setQScore(Global.DEBT, Integer.parseInt(doc.getLong("Score").toString()));
                    Global.user.setQScore(Global.TAXES, Integer.parseInt(doc.getLong("Score").toString()));
                    Global.user.setQScore(Global.INVESTMENTS, Integer.parseInt(doc.getLong("Score").toString()));
                }
            }
        });
        return true;
    }

    public String checkUser(String user){
        final String[] val = {""};
        //FINDS IF USERNAME EXISTS INSIDE FIRESTORE DATABASE
        fStore.collection("Emails").document(user).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    //RETURNS USERTAKEN
                    val[0] = "Username is taken!";
                }
            }
        });
        //NEEDS FIXING

        return val[0];
    }

    public String checkPass(String pass){
        String send = "";
        boolean hasUpper = false;
        for(char c : pass.toCharArray()){
            if(Character.isUpperCase(c)){
                hasUpper = true;
            }
        }

        if(!hasUpper){
            send += "Password must have an uppercase letter\n";
        }
        if(pass.length() < 8){
            send +=  "Password must be atleast 8 letters long!\n";
        }


        return send;
    }

    public void setProfPicture(Uri uri){
        UserProfileChangeRequest req = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(req).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("INFO", "CHANGED"); //CHANGE THIS TO SOMETHING ELSE
                }

            }
        });
    }

    public void updateScore(String lesson){
        Log.d("VALUE", String.valueOf(Global.user.getQScore(lesson)));
        fStore.collection(lesson).document(fAuth.getCurrentUser().getDisplayName())
                .update(
                        "Score", Global.user.getQScore(lesson)
                );
    }

    public void getScore(Context context, String lesson) {
        fStore.collection(lesson).document(Global.user.getUsername()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                if (doc.exists()) {
                    System.out.println(doc.getData().get("Score"));
                } else {
                    Toast.makeText(context, "DID NOT WORK", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void write(HashMap<String, ?> map, String collection, String document){

    }

    public void addStudent(){
        // SUGGESTION: TEST OUT USING COLLECTIONS INSIDE FIELDS
        // FIGURE OUT HOW TO USE TEACHER / STUDENT SIDE UI
        // APPEND TO CLASSES
        HashMap<String, Object> test = new HashMap<>();
        test.put("Student1", 13);
        fStore.collection("Classes").document("test");
    }

}
