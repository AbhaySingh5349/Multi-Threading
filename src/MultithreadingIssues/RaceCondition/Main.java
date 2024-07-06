package MultithreadingIssues.RaceCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        RaceCondition raceConditionIssue = new RaceCondition();

        ExecutorService es = Executors.newFixedThreadPool(4);

        for(int i=0;i<200000;i++){
            es.submit(raceConditionIssue);
        }

        try {
            es.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        es.shutdown();

        System.out.println(raceConditionIssue.getCount());
    }
}
