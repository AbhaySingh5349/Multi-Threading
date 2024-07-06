package MultithreadingIssues.MemoryConsistency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        ReadWriteTask write = new ReadWriteTask("write");
        ReadWriteTask read = new ReadWriteTask("read");

        es.submit(write);
        es.submit(read);

        try {
            es.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("In main: " + Thread.currentThread().getName());

        es.shutdown();
    }
}
