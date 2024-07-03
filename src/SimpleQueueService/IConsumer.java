package SimpleQueueService;

public interface IConsumer {
    void consume(ConsumerMessage msg);
    boolean isAvailable();
}
