package CreatingThreads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static BigInteger factorial(int num){
        BigInteger fact = BigInteger.ONE;
        for(int i=1;i<=num;i++){
            fact = fact.multiply(BigInteger.valueOf(i));
        }

        return fact;
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(100 ,200 ,300 ,400 ,500 ,600 ,700 ,800 ,900 ,1000);

//        long start = System.currentTimeMillis();
//
//        // "stream().parallel()" internally uses "max number of available processors" to create threads
//        input.stream().parallel().forEach(num -> {
//            System.out.println(Thread.currentThread().getName() + " for factorial of " + num + " is: " + factorial(num));
//        });
//        long end = System.currentTimeMillis();
//
//        System.out.println("time taken: " + (end - start));

//        long start = System.currentTimeMillis();
//        input.stream().forEach(num -> {
//            FactorialMT mt = new FactorialMT(num);
//
//            // number of threads created can be > "max number of available processors"
//            Thread thread = new Thread(mt);
//            thread.start();
//
//            try {
//                thread.join(); // sequential execution of threads
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        long end = System.currentTimeMillis();
//
//        System.out.println("time taken: " + (end - start));

        // get total execution time for all threads combined from "main thread"
        List<Thread> threads = new ArrayList<>();

        long start = System.currentTimeMillis();
        input.stream().forEach(num -> {
            FactorialMT mt = new FactorialMT(num);

            Thread thread = new Thread(mt);
            thread.start(); // starting all threads in parallel

            threads.add(thread);
        });

        threads.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // threads started one after other but main thread won't reach here till we execute all threads, hence parallel execution
        long end = System.currentTimeMillis();

        System.out.println("time taken: " + (end - start));
    }
}
