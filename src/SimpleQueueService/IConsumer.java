package SimpleQueueService;

public interface IConsumer {
    void consume(ConsumerMessage msg);
}
