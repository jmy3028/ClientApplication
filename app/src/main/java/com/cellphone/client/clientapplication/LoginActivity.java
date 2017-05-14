package com.cellphone.client.clientapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.cellphone.client.clientapplication.client.ClientActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mIdView;
    private EditText mPasswordView;
    private DatabaseReference mMyRef;
    private boolean mLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ID , Password 화면 구현
        mIdView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        // firebase와 연동
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mMyRef = mDatabase.getReference("Client");

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                //로그인시 Id가 있는지 없는지 판별을 도와줌.
                mLogin = false;
                mMyRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //아이디 공백 체크
                        if (!mIdView.getText().toString().equals("")) {
                            //비밀번호 공백 체크
                            if (!mPasswordView.getText().toString().equals("")) {
                                //DataBase에 중복 아이디 있는지 확인
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    if (mIdView.getText().toString().equals(dataSnapshot1.getKey())) {
                                        Toast.makeText(LoginActivity.this, "로그인이 되었습니다!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, ClientActivity.class);
                                        startActivity(intent);
                                        mLogin = true;
                                        mMyRef.removeEventListener(this);
                                    }
                                }
                                if (!mLogin) {
                                    Toast.makeText(LoginActivity.this, "해당 아이디가 없습니다.\n회원가입을 해주세요."
                                            , Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "ID를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        }
                        mMyRef.removeEventListener(this);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                break;
            case R.id.sign_up_button:
                //회원 가입 버튼을 눌렀을때 새로운 회원가입 화면으로 전환
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}

