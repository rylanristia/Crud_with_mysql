package com.rylanristia.crudwithmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void InputSaran(View view) {
        Intent intentInput = new Intent(this, InputForm.class);
        startActivity(intentInput);
    }

    public void ListSaran(View view) {
        onBackPressed();
    }
}