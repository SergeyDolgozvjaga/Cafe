package com.sergeydolzgozvjaga.cafe.CafeWorkers;

import com.sergeydolzgozvjaga.cafe.Model.Dishes;
import com.sergeydolzgozvjaga.cafe.Model.Order;
import com.sergeydolzgozvjaga.cafe.Model.Table;
import java.util.logging.Logger;

/**
* Class describes
* how long time will Table wait for Cook an Order
* */
public class Waiter implements Runnable {

    private final Logger logger = Logger.getLogger(String.valueOf(Waiter.class));
    public boolean continueWorking = true;

    @Override
    public void run() {
        logger.info("start run()");
        Manager manager = Manager.getInstance();

        while (continueWorking || !manager.getDishesQueue().isEmpty()){
            if (!manager.getDishesQueue().isEmpty()){
                // carrying ready order (относим готовый заказ)
                Dishes dishes = manager.getDishesQueue().poll();
                System.out.println("Waiter carry's order to table №" + dishes.getTableNumber());
            }
            else {
                // get new Order
                Table table = manager.getNextTable();
                Order order = table.getOrder();
                System.out.println("Receive order from table №" + order.getTableNumber());
                manager.getOrderQueue().add(order);
            }
            try{
                Thread.sleep(100);
            } catch (InterruptedException ex){
                logger.warning(ex.getMessage());
            }
        }
        logger.info("exit run()");
    }
}
