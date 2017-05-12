package com.cellphone.client.clientapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    // UI references.
    private AutoCompleteTextView mIdView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ID , Password 화면 구현
        mIdView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                break;
            case R.id.sign_up_button:
                //회원 가입 버튼을 눌렀을때 새로운 회원가입 화면으로 전환
                Intent intent = new Intent(this,SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}

