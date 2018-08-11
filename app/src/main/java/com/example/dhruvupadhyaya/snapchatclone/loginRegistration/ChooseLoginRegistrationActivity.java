package com.example.dhruvupadhyaya.snapchatclone.loginRegistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dhruvupadhyaya.snapchatclone.R;

public class ChooseLoginRegistrationActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mLogin;
    private Button mRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_registration);

        buttonSetUp();
        mLogin.setOnClickListener(this);
        mRegistration.setOnClickListener(this);
    }

    private void buttonSetUp(){

        mLogin = findViewById(R.id.loginButtonId);
        mRegistration = findViewById(R.id.regButtonId);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.loginButtonId:
                Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent1);
                //  return;
                break;

            case R.id.regButtonId:
                Intent intent2 = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent2);
                // return;
                break;
        }

    }
}
