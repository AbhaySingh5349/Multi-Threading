package StockExchange.NaiveApproach;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StockExchange se = new StockExchange();

        OrderExecutor oe1 = new OrderExecutor();
        OrderExecutor oe2 = new OrderExecutor();

        se.registerExecutor(oe1);
        se.registerExecutor(oe2);

        while (true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if(input.equals("exit")){
                break;
            }

            final String[] splits = input.split(" ");
            final String operation = splits[0];

            if(operation.equals("order")){
                final Integer val = Integer.parseInt(splits[1]);
            }else if(operation.equals("sell")){

            }
        }
    }
}
