package com.example.shaw.anew;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class views extends AppCompatActivity
{

    DatabaseReference databaseitems;
    ListView listviewitems;
    List<items> itemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);

        databaseitems = FirebaseDatabase.getInstance().getReference();

        listviewitems = (ListView) findViewById(R.id.listviewitems);

        itemsList = new ArrayList<>();


    }


    private void updatedialog(final String itemid, String itemname, String itemtype){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View viewdialog = inflater.inflate(R.layout.update, null);


        dialog.setView(viewdialog);

        final EditText updateitem = viewdialog.findViewById(R.id.updateitem);
        final Spinner updatetype =  viewdialog.findViewById(R.id.updatespinner);
        final Button updatebutton = viewdialog.findViewById(R.id.updatebutton);
        final Button deletebutton = viewdialog.findViewById(R.id.deletebutton);





        dialog.setTitle("Updating Items" +itemname);

        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();


        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = updateitem.getText().toString().trim();
                String type = updatetype.getSelectedItem().toString();

                if(TextUtils.isEmpty(name)){
                    updateitem.setError("Item Daaliye");
                    return;
                }

                updateitem(itemid,name,type);
                alertDialog.dismiss();
            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(itemid);
            }

            private void delete(String itemid) {

                String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();

                DatabaseReference data = FirebaseDatabase.getInstance().getReference().child(uid).child(itemid);
                data.removeValue();

                Toast.makeText(views.this, "Item Deleted!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean updateitem(String id, String name, String type){
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child(id);
        items item = new items(id,name,type);
        databaseReference.setValue(item);
        Toast.makeText(this,"Item Updated Successfully", Toast.LENGTH_LONG).show();

        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        databaseitems.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

              itemsList.clear();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();
                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren())
                {

                    items itemobj=itemSnapshot.getValue(items.class);
                    itemsList.add(itemobj);

                }

                itemslist adapter = new itemslist(views.this,itemsList);
                listviewitems.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    listviewitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            items item = itemsList.get(position);
            updatedialog(item.getItemId(),item.getItem_name(),item.getItem_type());

        }
    } );

    }




    }





