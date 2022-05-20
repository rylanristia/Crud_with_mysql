package com.rylanristia.crudwithmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InputForm extends AppCompatActivity {
    EditText nama, email, komentar, pertanyaan;
    Button BtnProses, BtnBatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);

        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        komentar = findViewById(R.id.komentar);
        pertanyaan = findViewById(R.id.pertanyaan);

        BtnProses = findViewById(R.id.BtnProses);
        BtnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proses_data();
            }
        });

        BtnBatal = findViewById(R.id.BtnBatal);
        BtnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    void proses_data() {
        String url = "http://127.0.0.1:8000/api/index";
        StringRequest respon = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> form = new HashMap<>();
                form.put("nama", nama.getText().toString());
                form.put("email", email.getText().toString());
                form.put("komentar", komentar.getText().toString());
                form.put("pertanyaan", pertanyaan.getText().toString());
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(respon);
    }
}