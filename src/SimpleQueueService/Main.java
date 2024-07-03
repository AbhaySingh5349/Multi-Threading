package SimpleQueueService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SQS sqs = new SQS();

        AdditionConsumer ac1 = new AdditionConsumer();
        AdditionConsumer ac2 = new AdditionConsumer();

        sqs.registerConsumer(ac1);
        sqs.registerConsumer(ac2);

//        ConsumerMessage m1 = new ConsumerMessage(1, 2);
//        ConsumerMessage m2 = new ConsumerMessage(2,3);
//
//        sqs.publish(m1);
//        sqs.publish(m2);

        while(true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if(input.equals("exit")){
//                sqs.releaseAllWorkers();
                break;
            }

            final String[] splits = input.split(" ");
            final String operation = splits[0];

            if(operation.equals("pub")){
                final int a = Integer.parseInt(splits[1]);
                final int b = Integer.parseInt(splits[2]);

                sqs.publish(new ConsumerMessage(a, b));
            }
        }


    }
}
