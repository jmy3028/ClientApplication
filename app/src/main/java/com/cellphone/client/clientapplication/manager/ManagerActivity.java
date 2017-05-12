package com.cellphone.client.clientapplication.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cellphone.client.clientapplication.R;

/**
 *  관리자 모드시 실행될 Activity
 */

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
    }
}
