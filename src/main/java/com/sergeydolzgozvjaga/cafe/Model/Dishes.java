package com.sergeydolzgozvjaga.cafe.Model;

/**
* Class describes Dishes
* */
public class Dishes {
    private byte tableNumber;

    public Dishes(byte tableNumber){
        this.tableNumber = tableNumber;
    }

    public byte getTableNumber(){
        return tableNumber;
    }
}
