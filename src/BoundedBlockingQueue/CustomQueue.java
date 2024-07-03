package BoundedBlockingQueue;

import java.util.ArrayList;
import java.util.List;

// State -> size of list
// "this" lock

public class CustomQueue {
    private final int maxSize;
    private final List<Integer> items;

    public CustomQueue(int maxSize){
        this.maxSize = maxSize;
        this.items = new ArrayList<>();
    }

    public void enqueue(Integer num){
        synchronized (this){
            while (items.size() == maxSize){
//            throw new RuntimeException("Q Out Of Bounds");

                // make thread waiting until space is available in Q

                System.out.println(Thread.currentThread().getName() + " enqueue is waiting for " + num);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(num + " enqueued by " + Thread.currentThread().getName());
            items.add(num);

            this.notifyAll();
        }
    }

    public void dequeue(){
        synchronized (this){
            while (items.isEmpty()){
//            throw new RuntimeException("No items available");

                System.out.println(Thread.currentThread().getName() + " dequeue is waiting");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            final Integer num = items.remove(0);
            System.out.println(num + " dequeued by " + Thread.currentThread().getName());

            this.notifyAll();
        }
    }

    public void printList(){
        for(Integer item : items){
            System.out.println("Item val " + item);
        }
    }
}
