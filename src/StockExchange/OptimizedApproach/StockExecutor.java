package StockExchange.OptimizedApproach;

import java.util.List;

public class StockExecutor implements Runnable{
    final List<Order> orders;
    final Stock stock;
    final IExecutor executor;

    public StockExecutor(List<Order> orders, Stock stock, IExecutor executor){
        this.orders = orders;
        this.stock = stock;
        this.executor = executor;
    }

    @Override
    public void run() {
        while(true){
            final MatchingOrder matchingOrder; // order matching remains sequential but we don't acquire locks on other stocks

            // instead of acquiring lock on all stock orders, we can acquire it for particular stock
            synchronized (stock){
                while (orders.isEmpty()){
                    try {
                        stock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                matchingOrder = findMatchingOrder();
                if (matchingOrder == null){
                    try {
                        stock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    orders.remove(matchingOrder.o1);
                    orders.remove(matchingOrder.o2);
                }
            }
            executor.consumeStockOrder(matchingOrder); // executing orders in parallel
        }
    }

    private MatchingOrder findMatchingOrder(){
        for(Order o1 : orders){
            for(Order o2 : orders){
                if(o1.id.equals(o2.id)){
                    continue;
                }

                if(o1.userId.equals(o2.userId) && o1.stock.symbol.equals(o2.stock.symbol) && o1.price.equals(o2.price) && !o1.orderType.equals(o2.orderType)){
                    return new MatchingOrder(o1, o2);
                }
            }
        }
        return null;
    }
}
