package ExecutorService.ParallelExecutionExample;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        AirIndia airIndia = new AirIndia();
        Indigo indigo = new Indigo();
        Vistara vistara = new Vistara();

        long start = System.currentTimeMillis();

        Future<?> f1 = es.submit(airIndia);
        Future<?> f2 = es.submit(indigo);
        Future<?> f3 = es.submit(vistara);

        try{
            f1.get();
            f2.get();
            f3.get();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        long end = System.currentTimeMillis();

        System.out.println("time taken: " + (end-start));

        es.shutdown();
    }
}
