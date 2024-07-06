package MultithreadingIssues.RaceCondition;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition implements Runnable{
//    private int count;

    private AtomicInteger count;

    public RaceCondition(){
//        this.count = 0;

        count = new AtomicInteger(0);
    }

    @Override
    public void run() {
//        increment();
//        threadSafeIncrement();
        atomicIncrement();
    }

    /*
    private void increment(){
        count++;
    }

    private synchronized void threadSafeIncrement(){
        count++;
    }
    */

    public void atomicIncrement(){
        count.getAndAdd(1);
    }

    public int getCount(){
//        return this.count;

        return count.get();
    }
}
