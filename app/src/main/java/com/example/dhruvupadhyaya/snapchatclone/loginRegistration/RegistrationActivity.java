package com.example.dhruvupadhyaya.snapchatclone.loginRegistration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhruvupadhyaya.snapchatclone.MainActivity;
import com.example.dhruvupadhyaya.snapchatclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mName;
    private Button mRegister;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        mEmail = findViewById(R.id.emailId);
        mPassword = findViewById(R.id.passwordId);
        mRegister = findViewById(R.id.registerId);
        mName = findViewById(R.id.nameId);

        mAuth = FirebaseAuth.getInstance();


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String name = mName.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {


                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i("TAG", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String userId = user.getUid();

                                    Toast.makeText(RegistrationActivity.this, "Success", Toast.LENGTH_SHORT).show();

                                    FirebaseDatabase db = FirebaseDatabase.getInstance();
//                                    DatabaseReference rootRef = db.getReference();
//
//                                    rootRef.child(userId).child("Email").setValue(email);
//                                    rootRef.child(userId).child("Password").setValue(password);

                                    DatabaseReference currentUserDb = db.getReference().child("users").child(userId);


                                    Map userInfo = new HashMap<>();
                                    userInfo.put("email", email);
                                    userInfo.put("name", name);
                                    userInfo.put("profileImageUrl", "default");
                                    currentUserDb.updateChildren(userInfo);

                                    startActivity(new Intent(RegistrationActivity.this,MainActivity.class));




                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });


            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
        //Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(RegistrationActivity.this, "Already In", Toast.LENGTH_SHORT).show();

    }


}
