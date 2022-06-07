package Global;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import database.User;

public class Global extends AppCompatActivity {
    User user;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public User getUser(){
        String fUser = fAuth.getCurrentUser().getUid().toString();
        String email = fAuth.getCurrentUser().getDisplayName().toString();
        //fStore.document(fUser)

        return null;
    }

    public void switchActivities(Context context, Class c){
        Intent switchActivityIntent = new Intent (context, c);
        startActivity(switchActivityIntent);
    }
}
