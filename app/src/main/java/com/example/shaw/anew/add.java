package com.example.shaw.anew;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class add extends AppCompatActivity implements View.OnClickListener
{

    EditText newitem;
    Spinner type_item;
    Button submitadd,gotoview;

    DatabaseReference databaseitems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        databaseitems = FirebaseDatabase.getInstance().getReference();

    newitem = (EditText) findViewById(R.id.newitem);
    submitadd = (Button) findViewById(R.id.submitadd);
    type_item = (Spinner) findViewById(R.id.type_item);
    gotoview = (Button) findViewById(R.id.gotoview);


    submitadd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            entry();
        }
    });
    gotoview.setOnClickListener(this);
    }



    private void entry(){

        String new_item_added = newitem.getText().toString().trim();
        String newtype = type_item.getSelectedItem().toString();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if(!new_item_added.isEmpty()){

            String id = databaseitems.push().getKey();

            items item = new items(id, new_item_added, newtype);

            databaseitems.child(user.getUid()).child(id).setValue(item);

            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
            return;
        }



    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submitadd:
                entry();
                break;
            case R.id.gotoview:
                startActivity(new Intent(this, views.class));
                break;

        }
    }
}
