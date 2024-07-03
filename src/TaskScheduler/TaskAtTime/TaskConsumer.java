package TaskScheduler.TaskAtTime;

public class TaskConsumer implements IConsumer {
    @Override
    public void consumeTaskMessage(String msg) {
        System.out.println("Task consumed: " + msg + " by " + Thread.currentThread().getName());
    }
}
