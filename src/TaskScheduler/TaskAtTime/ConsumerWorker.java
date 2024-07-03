package TaskScheduler.TaskAtTime;

import java.util.Date;
import java.util.PriorityQueue;

public class ConsumerWorker implements Runnable{
    private final PriorityQueue<Task> pq;
    final IConsumer consumer;

    public ConsumerWorker(PriorityQueue<Task> pq, IConsumer consumer) {
        this.pq = pq;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        // so that thread does not die after consuming once
        while(true){
            final Task task;
            synchronized (pq){
                System.out.println("waiting: " + Thread.currentThread().getName() + " size: " + pq.size());
                while(pq.isEmpty() || pq.peek().millisEpoch < System.currentTimeMillis()){
                    try {
                        pq.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                task = pq.poll();

                pq.notifyAll();
            }

            assert task != null;
            consumer.consumeTaskMessage(task.msg); // once message was retrieved from Q, we release the lock allowing parallel consumption of messages
        }
    }
}
