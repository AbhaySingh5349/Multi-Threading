package BoundedBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

// State -> size of list
// "this" lock

public class SyncBlockCustomQueue {
    private final int maxSize;
    private final Queue<Integer> items;

    public SyncBlockCustomQueue(int maxSize){
        this.maxSize = maxSize;
        this.items = new LinkedList<>();
    }

    public void enqueue(Integer num){
        synchronized (this){
            while (items.size() == maxSize){
                // make thread waiting until space is available in Q

                System.out.println(Thread.currentThread().getName() + " enqueue is waiting to add " + num);
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

                System.out.println(Thread.currentThread().getName() + " dequeue is waiting to remove");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            final Integer num = items.poll();
            System.out.println(num + " dequeued by " + Thread.currentThread().getName());

            this.notifyAll();
        }
    }

    public void printList(){
        synchronized (this) {
            for (Integer item : items) {
                System.out.println("Item val: " + item);
            }
        }
    }
}
