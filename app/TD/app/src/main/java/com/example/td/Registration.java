package com.example.td;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Registration extends AppCompatActivity {
ImageView backImage;
TextView btnAdd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnAdd=findViewById(R.id.addb);
        backImage=findViewById(R.id.back_conn);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent=new Intent(Registration.this, MainActivity.class);
                startActivity(monIntent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add=new Intent(Registration.this, MainActivity3.class);
                startActivity(add);
            }
        });
    }
}