package com.example.td;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
Button share;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        share=findViewById(R.id.shareButton);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share=new Intent(Intent.ACTION_SEND);
                String AppUrl="YOUR_PLASTORE_LINK";
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Download now");
                share.putExtra(Intent.EXTRA_TEXT, AppUrl);
                startActivity(Intent.createChooser(share, "share via"));
            }
        });
    }
}