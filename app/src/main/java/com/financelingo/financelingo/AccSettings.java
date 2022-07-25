package com.financelingo.financelingo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import Global.Global;

public class AccSettings extends AppCompatActivity {
    FirebaseUser user;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);

        user = FirebaseAuth.getInstance().getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
    }

    public void changeEmail(View v){

        //GET EMAIL FROM USER
        //PASS INTO UPDATE EMAIL`
        user.updateEmail("BigGiantFart@gmail.com").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    fStore.collection("User").document(user.getUid() + " + " + Global.user.getEmail()).delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Global.user.setEmail("BigGiantFart@gmail.com");//SET TO NEW EMAIL
                                Log.d("INFO", user.getUid() + " + " + Global.user.getEmail());
                                HashMap<String, String> newMap = new HashMap<>();

                                newMap.put("email", Global.user.getEmail()); // CHANGE TO USER INPUT EMAIL (NEW EMAIL)
                                newMap.put("id", Global.user.getId());
                                newMap.put("username", Global.user.getUsername());

                                fStore.collection("User")
                                        .document(user.getUid() + " + " + Global.user.getEmail())
                                        .set(newMap);
                                Toast.makeText(AccSettings.this, "DELETED", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(AccSettings.this, "FAILED", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    
}
