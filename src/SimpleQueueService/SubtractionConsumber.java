package SimpleQueueService;

public class SubtractionConsumber implements IConsumer{
    @Override
    public void consume(ConsumerMessage msg) {
        try {
            Thread.sleep(3000); // consumption is a long process, so we want to run things in parallel
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Subtraction by " + Thread.currentThread().getName() + " -> " + (msg.a - msg.b));
    }
}
