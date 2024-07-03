package BoundedBlockingQueue;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomQueue cq = new CustomQueue(2);

        while(true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if(input.equals("exit")){
                break;
            }

            final String[] splits = input.split(" ");
            final String operation = splits[0];

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(operation.equals("en")){
                        final int num = Integer.parseInt(splits[1]);
                        cq.enqueue(num);
                    }else{
                        cq.dequeue();
                    }
                }
            }).start();
        }

        /*
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cq.enqueue(1);
                cq.enqueue(2);
                cq.enqueue(3);
                cq.enqueue(4);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                cq.enqueue(5);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cq.dequeue();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                cq.dequeue();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                cq.dequeue();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                cq.dequeue();
            }
        });

        t1.start();
        t2.start();
         */
    }
}
