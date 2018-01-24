package com.my.new2pma;

/**
 * Created by ajay on 1/18/2018.
 */

public class Lenders {
    String Name;
    int amount;

    public Lenders(String name, int amount) {
        Name = name;
        this.amount = amount;
    }

    public String getName() {
        return Name;
    }

    public int getAmount() {
        return amount;
    }


}
