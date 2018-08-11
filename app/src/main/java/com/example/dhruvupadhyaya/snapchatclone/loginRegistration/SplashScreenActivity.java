package com.example.dhruvupadhyaya.snapchatclone.loginRegistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dhruvupadhyaya.snapchatclone.MainActivity;
import com.example.dhruvupadhyaya.snapchatclone.R;
import com.example.dhruvupadhyaya.snapchatclone.loginRegistration.ChooseLoginRegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {

    public static boolean started = false;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null ){
            Intent intent1 = new Intent(getApplication(),MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
            finish();
            return;
        }else {
            Intent intent1 = new Intent(getApplication(),ChooseLoginRegistrationActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
            finish();
            return;

        }
    }
}
