package ExecutorService.ParallelExecutionExample;

public class AirIndia implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("AirIndia response " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
