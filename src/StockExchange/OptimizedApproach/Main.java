package StockExchange.OptimizedApproach;

import StockExchange.OptimizedApproach.StockExchange;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        StockExchange se = new StockExchange();

        OrderExecutor oe1 = new OrderExecutor();
        OrderExecutor oe2 = new OrderExecutor();
        OrderExecutor oe3 = new OrderExecutor();

        Stock st1 = new Stock("Reliance", "R");
        Stock st2 = new Stock("Tata", "T");

        se.registerExecutor(st1, oe1);
        se.registerExecutor(st1, oe2);
        se.registerExecutor(st2, oe3);

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
