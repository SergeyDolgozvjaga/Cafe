package com.sergeydolzgozvjaga.cafe.CafeWorkers;

import com.sergeydolzgozvjaga.cafe.Model.Dishes;
import com.sergeydolzgozvjaga.cafe.Model.Order;

import java.util.logging.Logger;

/**
* Class describes behavior
* of cooking dishes
* */
public class Cook implements Runnable {

    private final Logger logger = Logger.getLogger(String.valueOf(Cook.class));
    public boolean continueWorking = true;

    @Override
    public void run() {
        logger.info("start run()");
        boolean needToWait;
        while (continueWorking || needToCookOrders()){
            try{
                synchronized (this){
                    needToWait = !needToCookOrders();
                    if (!needToWait){ cooking();
                    }
                } if (continueWorking && needToWait){
                    System.out.println("Cooker is rest");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e){
                logger.warning(e.getMessage());
            }
        }
        logger.info("exit run()");
    }

    /**
    * Method show , is present orders in Queue
    * @return flag (true/false) is Queue empty, or no
    * */
    private boolean needToCookOrders(){
        return !Manager.getInstance().getOrderQueue().isEmpty();
    }

    /**
    * Method show behavior
    * of cooking dishes
    * */
    private void cooking() throws InterruptedException {
        logger.info("start cooking()");
        Manager manager = Manager.getInstance();
        // cooker get order from queue
        Order order = manager.getOrderQueue().poll();
        System.out.println(String.format("Order will cook %d ms for table №%d", order.getTime(), order.getTableNumber()));
        Thread.sleep(order.getTime());
        Dishes dishes = new Dishes(order.getTableNumber());
        System.out.println(String.format("Order for table №%d is ready", dishes.getTableNumber()));
        manager.getDishesQueue().add(dishes);
        logger.info("exit cooking");
    }
}
