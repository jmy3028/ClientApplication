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

import java.util.Iterator;

public class SignUpActivity extends AppCompatActivity {

    private EditText mIdEdit;
    private EditText mPassEdit;
    private EditText mNameEdit;
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
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mMyRef = mDatabase.getReference("Client");


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_close_button:
                //닫기 버튼 눌렀을때 창 종료.
                finish();
                break;
            case R.id.sign_up_button:
                mMyRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (mIdEdit.getText().toString().equals(dataSnapshot1.getKey())) {
                                mMyRef.removeEventListener(this);
                                Toast.makeText(SignUpActivity.this, "해당 아이디는 존재 합니다.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(!mIdEdit.getText().toString().equals("")
                                && !mPassEdit.getText().toString().equals("")
                                && !mNameEdit.getText().toString().equals("")){

                            ClientModel clientModel = new ClientModel(mPassEdit.getText().toString(),
                                    mNameEdit.getText().toString(),0,"사원");
                            mMyRef.child(mIdEdit.getText().toString()).setValue(clientModel);
                            Toast.makeText(SignUpActivity.this, "회원가입이 완료 되었습니다!!", Toast.LENGTH_SHORT).show();
                            mMyRef.removeEventListener(this);
                            finish();
                        }else {
                            Toast.makeText(SignUpActivity.this, "공백없이 채워주세요!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        }
    }

}
