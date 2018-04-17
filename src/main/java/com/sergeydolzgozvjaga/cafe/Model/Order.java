package com.sergeydolzgozvjaga.cafe.Model;

/**
* Class describes
* how long time will cook
* the Order
* */
public class Order {
    private long time;
    private byte tableNumber;

    public Order(byte tableNumber){
        time = (long)(Math.random()*200);
        this.tableNumber = tableNumber;
    }

    public long getTime(){
        return time;
    }

    public byte getTableNumber(){
        return tableNumber;
    }
}
