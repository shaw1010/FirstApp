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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    EditText editTextemail2,editTextpassword2;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState );
    setContentView(R.layout.activity_main);

    mAuth = FirebaseAuth.getInstance();

    findViewById(R.id.signupbutton).setOnClickListener(this);
    findViewById(R.id.forgotpassword).setOnClickListener(this);
    findViewById(R.id.loginbutton).setOnClickListener(this);

    progressBar = (ProgressBar) findViewById(R.id.progressbar);
    editTextemail2 = (EditText) findViewById(R.id.editTextemail2);
    editTextpassword2 = (EditText) findViewById(R.id.editTextpassword2);
    }

    private void userlogin(){

        String email2 = editTextemail2.getText().toString().trim();
        String password2 = editTextpassword2.getText().toString().trim();

        if (email2.isEmpty()) {
            editTextemail2.setError("Enter Email!");
            editTextemail2.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email2).matches()) {
            editTextemail2.setError("Enter Valid Email");
            editTextemail2.requestFocus();
            return;
        }

        if (password2.isEmpty()) {
            editTextpassword2.setError("Password is necessary!");
            editTextpassword2.requestFocus();
            return;
        }

        if (password2.length() < 5) {
            editTextpassword2.setError("Minimum length of password should be 5");
            editTextpassword2.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        mAuth.signInWithEmailAndPassword(email2,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                progressBar.setVisibility(View.GONE);
                finish();
                Intent intent = new Intent(MainActivity.this, profileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    });

    }






    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.signupbutton:
                startActivity(new Intent(this, signin.class));
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, forgotpassword.class));
                break;
            case R.id.loginbutton:
                userlogin();
                break;
        }
    }


}



