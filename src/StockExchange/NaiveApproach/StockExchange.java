package StockExchange.NaiveApproach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockExchange {
    final HashMap<String, Order> ordersBook;
    private final List<IExecutor> executors;

    public StockExchange() {
        this.ordersBook = new HashMap<>();
        this.executors = new ArrayList<>();
    }

    public void placeOrder(Order order){
        synchronized (ordersBook){
            ordersBook.put(order.id, order);
            ordersBook.notifyAll();
        }
    }

    public void modifyOrder(){

    }

    public void cancelOrder(){

    }

    public void registerExecutor(IExecutor executor){
        executors.add(executor);
        new Thread(new StockExecutor(ordersBook, executor)).start();
    }

    public void orderStatus(){

    }
}
