package BoundedBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCustomQueue {
    private final int maxSize;
    private final Queue<Integer> items;

    private final ReentrantLock lock;
    private final Condition isFull; // condition ensures that producer (i.e. enqueue method) waits if queue is full
    private final Condition isEmpty; // condition ensures that consumer (i.e. dequeue method) waits if queue is empty

    public ReentrantLockCustomQueue(int maxSize) {
        this.maxSize = maxSize;
        this.items = new LinkedList<>();

        this.lock = new ReentrantLock(true); // true = fair lock
        this.isFull = lock.newCondition();
        this.isEmpty = lock.newCondition();
    }

    public void enqueue(Integer num) {
        lock.lock();
        try {
            while (items.size() == maxSize) {
                System.out.println(Thread.currentThread().getName() + " waiting to enqueue " + num);
                isFull.await(); // Wait if the queue is full
            }

            items.add(num);
            System.out.println(num + " enqueued by " + Thread.currentThread().getName());

            isEmpty.signalAll(); // wake up waiting consumers
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // best practice
        } finally {
            lock.unlock();
        }
    }

    public void dequeue() {
        lock.lock();
        try {
            while (items.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " waiting to dequeue");
                isEmpty.await();
            }

            Integer removed = items.poll();
            System.out.println(removed + " dequeued by " + Thread.currentThread().getName());

            isFull.signalAll(); // wake one waiting producer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void printQueue() {
        lock.lock();
        try {
            System.out.println("Current Queue: " + items);
        } finally {
            lock.unlock();
        }
    }
}
