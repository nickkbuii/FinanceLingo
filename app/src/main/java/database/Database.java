package database;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

import java.util.HashMap;

import Global.Global;

public class Database {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;


    public Database(){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
    }

    public boolean createAcc(Context context, String username, String email, String password){
        final boolean[] val = {true};
        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }

        //Creates new User onto firebase authentication
        fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(username, password, email, fAuth.getCurrentUser().getUid());

                            Toast.makeText(context, "ACCOUNT CREATED", Toast.LENGTH_SHORT).show();

                            //Adds to profile firebase firestore
                            fStore.collection("User")
                                    .document(fAuth.getCurrentUser().getUid() + " + " + user.getEmail())
                                    .set(user);

                            //Adds to email-username connection
                            HashMap<String, String> emailMap = new HashMap<>();
                            emailMap.put("Email", user.getEmail());

                            //Adds to allow login with username
                            fStore.collection("Emails")
                                    .document(user.getUsername())
                                    .set(emailMap);

                            //Adds to email-username connection
                            HashMap<String, Integer> qNumScore = new HashMap<>();

                            //Question and score
                            qNumScore.put("Question", Integer.valueOf(0));
                            qNumScore.put("Score", Integer.valueOf(0));

                            //Adds to allow login with username
                            fStore.collection("Budgeting")
                                    .document(user.getUsername())
                                    .set(qNumScore);

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

    public String checkUser(String user){
        final String[] val = {""};
        fStore.collection("Emails").document(user).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    val[0] = "Username is taken!";
                }
            }
        });

        return val[0];
    }

    public boolean login(Context context, String username, String password){
        final boolean[] val = {true};
        //Checks if User is logged in
        //TEMPORARY : AUTO LOGOUT -> ASK USER IF THEY WISH TO LOG OUT

        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }

        if(username.contains("@")){
            fAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        if(loadData(context)){
                            Global.user.setPw(password);
                        }
                    }
                    else{
                        val[0] = false;
                        Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            fStore.collection("Emails").document(username).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot doc) {
                    if(doc.exists()){
                        String email = doc.getString("Email");
                        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    if(loadData(context)){
                                        Global.user.setPw(password);
                                    }
                                }
                                else{
                                    Toast.makeText(context, "LOGIN FAIL", Toast.LENGTH_SHORT).show();
                                    val[0] = false;
                                }
                            }
                        });
                    }
                    else{
                        val[0] = false;
                        Toast.makeText(context, "NO USERNAME IN DATABASE", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return val[0];
    }

    public void updateEmail(Context context, String newEmail){
        user.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    fStore.collection("User").document(user.getUid() + " + " + Global.user.getEmail()).delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Global.user.setEmail(newEmail);//SET TO NEW EMAIL
                                        Log.d("INFO", user.getUid() + " + " + Global.user.getEmail());
                                        HashMap<String, String> newMap = new HashMap<>();

                                        newMap.put("email", Global.user.getEmail()); // CHANGE TO USER INPUT EMAIL (NEW EMAIL)
                                        newMap.put("id", Global.user.getId());
                                        newMap.put("username", Global.user.getUsername());

                                        fStore.collection("User")
                                                .document(user.getUid() + " + " + Global.user.getEmail())
                                                .set(newMap);

                                        fStore.collection("Emails").document(Global.user.getUsername()).update("Email", Global.user.getEmail());
                                        Toast.makeText(context, "DELETED", Toast.LENGTH_SHORT).show();
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

    public boolean loadData(Context context){
        //GRABS DATA FROM FIRESTORE DATABASE
        String getData = fAuth.getCurrentUser().getUid().toString() + " + " + fAuth.getCurrentUser().getEmail();
        Log.d("INFO", getData);
        fStore.collection("User").document(getData).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                if(doc.exists()){
                    String user = doc.getString("username");//Change to "Username"
                    String email = doc.getString("email");
                    String id = doc.getString("id");

                    Global.user = new User(user, email, id);

                    Lessons.setUser(Global.user.getUsername().toUpperCase());
                }
                else{
                    Toast.makeText(context, "DID NOT WORK", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fStore.collection("Budgeting").document("kru").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                if (doc.exists()) {
                    Global.user.setQScore(Integer.valueOf(doc.getLong("Score").toString()));
                    Global.user.setQNum(Integer.valueOf(doc.getLong("Question").toString()));
                }
            }
        });
        return true;
    }

    public void setProfPicture(Uri uri){
        UserProfileChangeRequest req = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(req).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d("INFO", "CHANGED"); //CHANGE THIS TO SOMETHING ELSE
                }
            }
        });
    }

}
