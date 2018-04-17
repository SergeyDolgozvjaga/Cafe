package com.sergeydolzgozvjaga.cafe;

import com.sergeydolzgozvjaga.cafe.CafeWorkers.Cook;
import com.sergeydolzgozvjaga.cafe.CafeWorkers.Waiter;

import java.util.logging.Logger;

/**
 * @Author Sergey Dolgozvjaga
 * */
public class MainCafe {

    private static final Logger logger = Logger.getLogger(String.valueOf(MainCafe.class));

    public static void main(String[] args) throws Exception{

        logger.info("start App");
        Waiter waiterTarget = new Waiter();
        Thread waiter = new Thread(waiterTarget);

        Cook cookTarget = new Cook();
        Thread cook = new Thread(cookTarget);

        waiter.start();
        cook.start();

        Thread.sleep(2000);
        waiterTarget.continueWorking = false;
        cookTarget.continueWorking = false;
        logger.info("exit App");
    }
}
