package StockExchange.OptimizedApproach;

public class MatchingOrder {
    public Order o1;
    public Order o2;
    public boolean isExecuted;

    public MatchingOrder(Order o1, Order o2) {
        this.o1 = o1;
        this.o2 = o2;
        this.isExecuted = false;
    }
}
