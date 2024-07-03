package TaskScheduler.TaskAtTime;

import java.util.Date;
import java.util.PriorityQueue;

public class TaskSchedulerAt {
    private final PriorityQueue<Task> pq;

    public TaskSchedulerAt(){
        this.pq = new PriorityQueue<>((a, b) -> {
            return a.millisEpoch < b.millisEpoch ? -1:1; // min heap
        });
    }

    public void registerConsumer(IConsumer consumer){
        // each thread is having its own Consumer Worker object which deals with its own work
        // since each worker is operating on same Q, we need to do synchronization at Q level
        new Thread(new ConsumerWorker(pq, consumer)).start();
    }

    public void scheduleAfter(String msg, Long millisEpoch){
        scheduleAt(msg, System.currentTimeMillis() + millisEpoch);
    }

    public void scheduleAt(String msg, Long millisSinceEpoch){
        Task task = new Task(msg, millisSinceEpoch);

        synchronized (pq){
            pq.add(task);
            pq.notifyAll();
        }
    }
}
