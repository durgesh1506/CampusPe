package com.example.campuspe.ui;

import java.util.ArrayList;

public class CanteenData {
    String name;
    ArrayList<FoodDetails> Menu;
    String upi;

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList< FoodDetails > getMenu() {
        return Menu;
    }

    public void setMenu(ArrayList< FoodDetails > menu) {
        Menu = menu;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public CanteenData(){

    }

    public CanteenData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
