package com.example.foody.model;

import android.widget.ImageView;

public class Catagory {
    private String Item;
    private String Image;

    public Catagory()
    {

    }
    public Catagory(String item, String image)
    {
        Item = item;
        Image = image;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
