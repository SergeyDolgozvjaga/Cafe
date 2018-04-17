package com.sergeydolzgozvjaga.cafe.CafeWorkers;

import com.sergeydolzgozvjaga.cafe.Model.Dishes;
import com.sergeydolzgozvjaga.cafe.Model.Order;
import com.sergeydolzgozvjaga.cafe.Model.Table;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
* Class describes manager,
* who take order from clients
*
* Class is Singleton pattern
* */
public class Manager {

    private static Manager ourInstance = new Manager();

    private final List<Table> cafeTables = new ArrayList<>(10);
    private int currentIndex = 0;

    private final Queue<Order> orderQueue = new ConcurrentLinkedDeque<>();
    private final Queue<Dishes> dishesQueue = new ConcurrentLinkedDeque<>();

    public synchronized static Manager getInstance(){
        return ourInstance;
    }

    private Manager(){
        // create  10 tables
        for (int i = 0; i < 10; i++) cafeTables.add(new Table());
    }

    /**
    * Method describes behavior
    * how manager take next table
    *
    * @return table number
    * */
    public synchronized Table getNextTable(){

        Table table = cafeTables.get(currentIndex);
        currentIndex = (currentIndex +1)% 10;
        return table;
    }

    /**
    * Method return queue of Orders
    * @return queue of Orders
    */
    public Queue<Order> getOrderQueue(){
        return orderQueue;
    }

    /**
    * Method return queue of Dishes
    * @return queue of Dishes
    * */
    public Queue<Dishes> getDishesQueue(){
        return dishesQueue;
    }
}
