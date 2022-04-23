package com.example.campuspe.ui;

import java.util.ArrayList;

public class CanteenData {
    String name;
    ArrayList<FoodDetails> Menu;
    String upi;

    public CanteenData(){

    }

    public CanteenData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
