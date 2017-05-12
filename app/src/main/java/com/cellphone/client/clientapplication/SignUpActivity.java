package com.cellphone.client.clientapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cellphone.client.clientapplication.model.ClientModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private EditText mIdEdit;
    private EditText mPassEdit;
    private EditText mNameEdit;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mMyRef;
    private String mIdKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //App Id , Password , name을 EditText와 연결
        mIdEdit = (EditText) findViewById(R.id.id_edit);
        mPassEdit = (EditText) findViewById(R.id.pass_edit);
        mNameEdit = (EditText) findViewById(R.id.name_edit);

        // firebase와 연동
        mDatabase = FirebaseDatabase.getInstance();
        mMyRef = mDatabase.getReference("Client");


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_close_button:
                //닫기 버튼 눌렀을때 창 종료.
                finish();
                break;
            case R.id.sign_up_button:

                mMyRef.orderByChild("Client").equalTo(mIdEdit.getText().toString())
                        .addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                if (dataSnapshot.getKey().equals(mIdEdit.getText().toString())) {
                                    Toast.makeText(SignUpActivity.this, "해당아이디가 이미 존재 합니다."
                                            , Toast.LENGTH_SHORT).show();
                                } else {
                                    //firebase에 휴대폰 번호(App ID)를 상단층으로 등록한후, 그 밑에 수하에 비밀번호,
                                    //이름, 포인트, 등급 순으로 데이터를 저장 한뒤에 가입화면 종료.
                                    ClientModel clientModel = new ClientModel(mPassEdit.getText().toString(),
                                            mNameEdit.getText().toString(), 0, "사원");
                                    mMyRef.child(mIdEdit.getText().toString()).setValue(clientModel);
                                    finish();
                                }

                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


        }
    }
}
