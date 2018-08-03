package com.example.shaw.anew;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signin extends AppCompatActivity implements View.OnClickListener{
    ProgressBar progressBar;
    EditText editTextemail,editTextpassword;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState );
        setContentView(R.layout.activity_signin);
        editTextemail = (EditText) findViewById(R.id.editTextemail);
        editTextpassword = (EditText) findViewById(R.id.editTextpassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        // findViewById(R.id.login).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    findViewById(R.id.signin).setOnClickListener(this);
    findViewById(R.id.alreadymember).setOnClickListener(this);
    }




    private void registerUser() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextemail.setError("Email Daaliye!");
            editTextemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Enter Valid Email");
            editTextemail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextpassword.setError("Password Daalo Bhai!");
            editTextpassword.requestFocus();
            return;
        }

        if (password.length() < 5) {
            editTextpassword.setError("Minimum length of password should be 5");
            editTextpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "User Registered Sucessfully", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(signin.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                   // Toast.makeText(getApplicationContext(), "User Registered Sucessfully", Toast.LENGTH_SHORT).show();
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "User Already Registered", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signin:
                registerUser();
                break;
            case R.id.alreadymember:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}