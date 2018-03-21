package com.example.genc4y10.firebaseinstagram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseInstagram extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_instagram);
        emailText=(EditText) findViewById(R.id.emailTextview);
        passwordText=(EditText) findViewById(R.id.passwordTextview);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

    }

    public void signUp(View view){
        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //başarılı olduğunda ne yapacaksa yazılır
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Kullanıcı oluşturuldu",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() { // başarısız olacaksa yapması gerekenler yazılır
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(e != null){
                            Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

                        }
                    }
        });

    }
    public void signIn(View view){
        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //başarılı olduğunda ne yapacaksa yazılır
                        if(task.isSuccessful()){
                            Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() { // başarısız olacaksa yapması gerekenler yazılır
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e != null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }
}
