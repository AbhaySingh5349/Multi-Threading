package SimpleQueueService;

import java.util.Queue;

public class ConsumerWorker implements Runnable{
    final Queue<ConsumerMessage> q;
    final IConsumer consumer;

    public ConsumerWorker(Queue<ConsumerMessage> q, IConsumer consumer) {
        this.q = q;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        // so that thread does not die after consuming once
        while(true){
            final ConsumerMessage msg;
            synchronized (q){
                while(q.isEmpty()){
                    try {
                        q.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                msg = q.remove();

                q.notifyAll();
            }
            consumer.consume(msg); // once message was retrieved from Q, we release the lock allowing parallel consumption of messages
        }
    }
}
