package StockExchange.NaiveApproach;

public class OrderExecutor implements IExecutor{
    @Override
    public void consumeStockOrder(MatchingOrder matchingOrder) {
        System.out.println("Executing orders: " + matchingOrder.o1.id + " and " + matchingOrder.o2.id);
    }
}
