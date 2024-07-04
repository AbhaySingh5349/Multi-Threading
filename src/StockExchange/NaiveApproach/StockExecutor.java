package StockExchange.NaiveApproach;

import java.util.HashMap;

public class StockExecutor implements Runnable{
    final HashMap<String, Order> ordersBook;
    final IExecutor executor;

    public StockExecutor(HashMap<String, Order> ordersBook, IExecutor executor){
        this.ordersBook = ordersBook;
        this.executor = executor;
    }

    @Override
    public void run() {
        while(true){
            MatchingOrder matchingOrder;

            // fetching matching orders from map is still sequential
            synchronized (ordersBook){
                while (ordersBook.isEmpty()){
                    try {
                        ordersBook.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                // find matching order else wait
                matchingOrder = findMatchingOrder();
                if (matchingOrder == null){
                    try {
                        ordersBook.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    ordersBook.remove(matchingOrder.o1.id);
                    ordersBook.remove(matchingOrder.o2.id);
                }
            }
            executor.consumeStockOrder(matchingOrder); // executing orders in parallel
        }
    }

    private MatchingOrder findMatchingOrder(){
        for(Order o1 : ordersBook.values()){
            for(Order o2 : ordersBook.values()){
                if(o1.id.equals(o2.id)){
                    continue;
                }

                if(o1.userId.equals(o2.userId) && o1.symbol.equals(o2.symbol) && o1.price.equals(o2.price) && !o1.orderType.equals(o2.orderType)){
                    return new MatchingOrder(o1, o2);
                }
            }
        }
        return null;
    }
}
