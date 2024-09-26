package CustomMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> mp = new CustomHashMap<>();
//        mp.put("a", 1);
//        mp.put("b", 2);
//        mp.put("c", 3);
//        mp.put("d", 4);
//        mp.put("e", 5);
//        mp.put("f", 6);
//        mp.put("g", 7);
//        mp.put("h", 8);
//        mp.put("i", 9);
//        mp.put("b", 10);
//
//        mp.remove("g");
//
//        LinkedList<String> keys = (LinkedList<String>) mp.keySet();
//        for (String key : keys){
//            System.out.println("key: " + key + " -> value:" + mp.get(key));
//        }
//
//        System.out.println(mp.containsKey("a"));

        ExecutorService executorService = Executors.newFixedThreadPool(4);

//        for (int i = 0; i < 1000; i++) {
//            executorService.submit(() -> {
//                String randomString = generateRandomString(6);
//                mp.put(randomString, 1); // Simulate storing in map
//            });
//        }

        // List to hold Future objects
        List<Future<?>> futures = new ArrayList<>();

        // Submit tasks to ExecutorService and store Futures
        for (int i = 0; i < 10000; i++) {
            Future<?> future = executorService.submit(() -> {
                String randomString = generateRandomString(6);
                mp.put(randomString, 1); // Simulate storing in map
            });
            futures.add(future);
        }

        executorService.shutdown();

        // Wait for all tasks to finish
        for (Future<?> future : futures) {
            try {
                future.get(); // This will block until the task is complete
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

//        for(int i=0;i<1000;i++){
//            String randomString = generateRandomString(6);
//            mp.put(randomString, 1);
//        }

        System.out.println(mp.size());
    }
}
