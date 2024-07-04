package StockExchange.OptimizedApproach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockExchange {
    private final HashMap<Stock, List<Order>> stockOrderMap; // maintaining order book per stock
    private final List<IExecutor> executors;

    public StockExchange() {
        this.stockOrderMap = new HashMap<>();
        this.executors = new ArrayList<>();
    }

    public void placeOrder(Order order){
        List<Order> orders = stockOrderMap.get(order.stock);

        synchronized (orders){
            orders.add(order);
            order.orderStatus = OrderStatus.ACCEPTED;
            orders.notifyAll();
        }
    }

    public void modifyOrder(){

    }

    public void cancelOrder(Order order){
        // here we need to check for lock, as some other thread might be processing

        Stock stock = order.stock;
        synchronized (stock){
            List<Order> orders = stockOrderMap.get(stock);

            order.orderStatus = OrderStatus.CANCELLED;
            orders.remove(order);
        }
    }

    public void registerExecutor(Stock stock, IExecutor executor){
        // since hash maps are not Thread-Safe (since this func may get called from multiple threads)
        synchronized (stockOrderMap){
            stockOrderMap.putIfAbsent(stock, new ArrayList<>());

            List<Order> orders = stockOrderMap.get(stock);

            executors.add(executor);
            new Thread(new StockExecutor(orders, stock, executor)).start();
        }
    }

    public Order getOrder(String orderId, Stock stock){
        // we took a local copy as "stockOrderMap" might be getting updated by other orders
        List<Order> orders = new ArrayList<>(stockOrderMap.get(stock));

        return orders.stream().filter(o -> o.id.equals(orderId)).findAny().orElse(null);
    }
}
