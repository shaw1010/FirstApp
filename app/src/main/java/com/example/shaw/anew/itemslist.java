package com.example.shaw.anew;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class itemslist extends ArrayAdapter<items> {

    private Activity context;
    private List<items> itemsList;



    public itemslist(Activity context, List<items>itemsList){
        super(context, R.layout.list, itemsList);
        this.context = context;
        this.itemsList = itemsList;}


        @NonNull
        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();

            View listViewItem = inflater.inflate(R.layout.list, null, true);

            TextView listview = (TextView) listViewItem.findViewById(R.id.listview);

            TextView listtype = (TextView) listViewItem.findViewById(R.id.listtype);

            items item = itemsList.get(position);

            listview.setText(item.getItem_name());
            listtype.setText(item.getItem_type());


            return listViewItem;

    }

    }


