package TaskScheduler.TaskAtTime;

public class TaskConsumer implements IConsumer {
    private final String id;

    public TaskConsumer(String id) {
        this.id = id;
    }

    @Override
    public void consumeTaskMessage(String msg) {
        System.out.println("Task consumed: " + msg + " by " + Thread.currentThread().getName());
    }
}
