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
                                System.out.println(Thread.currentThread().getName() + " sleeping until task time: " + waitTime);
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