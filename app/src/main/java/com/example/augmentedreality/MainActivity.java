package com.example.augmentedreality;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    private Button abtn;
    private Button cbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        abtn = findViewById(R.id.adminbtn);
        cbtn = findViewById(R.id.customerBtn);

        abtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, AdminLogin.class);

                finish();
                startActivity(i);
            }

        });

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Menu.class);

                finish();
                startActivity(i);
            }
        });

    }
}
