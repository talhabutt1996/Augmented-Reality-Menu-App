package com.example.augmentedreality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class AdminLogin extends AppCompatActivity {
    private EditText memailField;
    private EditText mpasswordFiedl;
    private Button mLoginBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth= FirebaseAuth.getInstance();

        memailField= (EditText) findViewById(R.id.emailField);
        mpasswordFiedl= (EditText) findViewById(R.id.passwordField);
        mLoginBtn= (Button) findViewById(R.id.loginBtn);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = firebaseAuth.getCurrentUser();
                if(mUser != null){

                    Toast.makeText(AdminLogin.this,"Enter Your Credentials", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(AdminLogin.this,"Not signed in", Toast.LENGTH_LONG).show();
                }
            }
        };

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(memailField.getText().toString())
                        && !TextUtils.isEmpty(mpasswordFiedl.getText().toString())){

                    String email = memailField.getText().toString();
                    String pwd = mpasswordFiedl.getText().toString();

                    login(email,pwd);

                }else{

                    Toast.makeText(AdminLogin.this,"Fields are emty", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void login(String email, String pwd) {
        mAuth.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          Toast.makeText(AdminLogin.this, "signed in", Toast.LENGTH_LONG).show();
                          Intent i = new Intent(AdminLogin.this, AdminPanel.class);

                          finish();
                          startActivity(i);
                      }else{

                          Toast.makeText(AdminLogin.this, "Enter Valid Credentials", Toast.LENGTH_LONG).show();
                      }
                    }
                });
    }




    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){

            mAuth.removeAuthStateListener(mAuthListener);

        }

    }

    public void btn_Backtomain(View view) {

        Intent i = new Intent(AdminLogin.this, MainActivity.class);

        finish();
        startActivity(i);

    }
}

