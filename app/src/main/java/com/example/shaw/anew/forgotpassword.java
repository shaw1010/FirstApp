package com.example.shaw.anew;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity implements View.OnClickListener {

    EditText editTextemail3;
    Button forgotpasswordbutton;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);


        editTextemail3 = (EditText) findViewById(R.id.editTextemail3);
        forgotpasswordbutton = (Button) findViewById(R.id.forgotpasswordbutton);
        firebaseAuth = FirebaseAuth.getInstance();

        forgotpasswordbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailreset = editTextemail3.getText().toString().trim();

                if(emailreset.isEmpty()){
                    Toast.makeText(forgotpassword.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(emailreset).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                            Toast.makeText(forgotpassword.this, "Password Reset Email Sent!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(forgotpassword.this, MainActivity.class));
                        }
                            else{

                                Toast.makeText(forgotpassword.this, "Error Occured!", Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
