package TaskScheduler.TaskAtTime;

public class Main {
    public static void main(String[] args) {
        TaskConsumer tc1 = new TaskConsumer();
        TaskConsumer tc2 = new TaskConsumer();

        final TaskSchedulerAt scheduler = new TaskSchedulerAt();

        scheduler.registerConsumer(tc1);
        scheduler.registerConsumer(tc2);

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
