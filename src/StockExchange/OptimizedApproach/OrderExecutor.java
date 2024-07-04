package StockExchange.OptimizedApproach;

public class OrderExecutor implements IExecutor {
    @Override
    public void consumeStockOrder(MatchingOrder matchingOrder) {
        matchingOrder.o1.orderStatus = OrderStatus.EXECUTING;
        matchingOrder.o2.orderStatus = OrderStatus.EXECUTING;

        System.out.println(Thread.currentThread().getName() + " Executing order: " + matchingOrder.o1.id + " and " + matchingOrder.o2.id);

        try {
            System.out.println(Thread.currentThread().getName() + " Executing order: " + matchingOrder.o1.id + " and " + matchingOrder.o2.id);
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " Executed order: " + matchingOrder.o1.id + " and " + matchingOrder.o2.id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        matchingOrder.o1.orderStatus = OrderStatus.EXECUTED;
        matchingOrder.o2.orderStatus = OrderStatus.EXECUTED;
    }
}
