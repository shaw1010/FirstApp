package com.example.shaw.anew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profileActivity extends AppCompatActivity implements View.OnClickListener{

    Button additem,viewitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        findViewById(R.id.addbutton).setOnClickListener(this);
        findViewById(R.id.viewbutton).setOnClickListener(this);


        additem = (Button) findViewById(R.id.addbutton);
        viewitems = (Button) findViewById(R.id.viewbutton);


    }




    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.addbutton:
                startActivity(new Intent(profileActivity.this, add.class));
                break;
            case R.id.viewbutton:
                startActivity(new Intent(profileActivity.this, views.class));
                break;
        }


    }
}
