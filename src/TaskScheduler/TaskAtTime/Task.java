package TaskScheduler.TaskAtTime;

public class Task {
    public String msg;
    public long millisEpoch;

    public Task(String msg, long millisEpoch){
        this.msg = msg;
        this.millisEpoch = millisEpoch;
    }
}
