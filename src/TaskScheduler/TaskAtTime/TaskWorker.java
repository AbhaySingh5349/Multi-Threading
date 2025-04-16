package TaskScheduler.TaskAtTime;

import java.util.PriorityQueue;

public class TaskWorker implements Runnable{
    private final PriorityQueue<Task> pq;
    final IConsumer consumer;

    public TaskWorker(PriorityQueue<Task> pq, IConsumer consumer) {
        this.pq = pq;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        // so that thread does not die after consuming once
        while(true){
            final Task task;
            synchronized (pq) {
                while (pq.isEmpty() || pq.peek().millisEpoch > System.currentTimeMillis()) {
                    try {
                        if (!pq.isEmpty()) {
                            long waitTime = pq.peek().millisEpoch - System.currentTimeMillis();
                            if (waitTime > 0) {
                                System.out.println(Thread.currentThread().getName() + " sleeping until task time: " + waitTime + " for " + pq.peek().msg);
                                pq.wait(waitTime); // wait for just right amount of time till next task is due
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + " waiting indefinitely because queue is empty.");
                            pq.wait(); // Wait indefinitely if empty
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return; // Exit thread gracefully
                    }
                }

                task = pq.poll();
                pq.notifyAll(); // Notify others waiting to enqueue or consume
            }

            assert task != null;
            consumer.consumeTaskMessage(task.msg); // once message was retrieved from Q, we release the lock allowing parallel consumption of messages
        }
    }
}

/*
why this is not optimal ?
-> This causes indefinite waiting even if the task is due in 10 milliseconds.
-> There's no mechanism to wake up after just the right time.
-> Go for Busy waiting (Time-based waiting missing)

synchronized (pq){
    System.out.println("waiting: " + Thread.currentThread().getName() + " size: " + pq.size());
    while(pq.isEmpty() || pq.peek().millisEpoch > System.currentTimeMillis()){
        try {
            pq.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    task = pq.poll();

    pq.notifyAll();
}
*/

/*
what happens when pq is initially empty, but 2 worker threads are initialized ?

-> Thread-1 acquires the lock on pq, enters the synchronized block
   Sees that pq.isEmpty() == true, hence Calls pq.wait(), which: Releases the lock on pq & Moves Thread-1 into the waiting state for pq

-> Thread-2 is now able to acquire the lock, because Thread-1 released it.
   Thread-2 does the same thing: enters the sync block, sees queue is empty, calls pq.wait(), and releases the lock.

-> both threads eventually call pq.wait(), but not at the same time.
   They do so sequentially, because only one thread at a time can be inside synchronized (pq)
*/

/*
let's say a task is added which is scheduled after 500 ms, how both threads will act now ?

-> Before the Task Is Added, both threads are in waiting state i.e none is acquiring lock

-> when task is added, both waiting threads are notified:
   let Thread-1 go to wait state ~ 500 ms & releases lock, allowing Thread-2 to proceed.
   Thread-2 acquires lock & also see Q is not empty, so go to wait state ~ 480 ms & releases lock

-> Both threads are now sleeping with a timeout (wait(timeLeft)) rather than waiting indefinitely
   When the "timeLeft" passes, (not same as notifyAll()) only the thread whose wait time is over will wake up & try to acquire lock
   but if in between "notifyAll()" is invoked, all threads wake up & try to acquire lock
*/