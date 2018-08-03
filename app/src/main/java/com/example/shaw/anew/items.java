package com.example.shaw.anew;

public class items {

    String itemId;
    String item_name;
    String item_type;

    public items(){


    }

    public items(String itemId, String item_name, String item_type) {
        this.itemId = itemId;
        this.item_name = item_name;
        this.item_type = item_type;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_type() {
        return item_type;
    }
}
