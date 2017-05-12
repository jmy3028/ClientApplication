package com.cellphone.client.clientapplication.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cellphone.client.clientapplication.R;

/**
 *  Client모드 일때 실행되는 Activity
 */
public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
    }
}
