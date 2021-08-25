package com.example.fariz.riset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    ImageView dog, cow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cow = (ImageView)findViewById(R.id.cow);
        dog = (ImageView)findViewById(R.id.dog);

        cow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start
            }
        });
    }
}
