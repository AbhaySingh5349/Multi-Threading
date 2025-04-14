package TaskScheduler.TaskAtTime;

// execute task at scheduled time or if consumer is not available, execute it ASAP after scheduled time
// extension can be scheduling at fixed intervals

public class Main {
    public static void main(String[] args) {
        TaskConsumer tc1 = new TaskConsumer("consumer1");
        TaskConsumer tc2 = new TaskConsumer("consumer2");

        final TaskSchedulerAt scheduler = new TaskSchedulerAt();

        scheduler.initializeWorker(tc1);
        scheduler.initializeWorker(tc2);

        scheduler.scheduleAfter("task1", (long) (1000));
        scheduler.scheduleAfter("task2", (long) (1000));
        scheduler.scheduleAfter("task3", (long) (1000));
        scheduler.scheduleAfter("task4", (long) (2000));
        scheduler.scheduleAfter("task5", (long) (500));

        try {
            Thread.sleep(3000);
            scheduler.scheduleAfter("task6 after sleep", (long) (500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
