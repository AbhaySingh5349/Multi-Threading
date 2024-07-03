package SimpleQueueService;

// only 1 of the consumers will be processing the message
// consumers receive message in FIFO order but we are not sure which consumer will actually process it as consumer can schedule it for later
// SQS does not maintain ordering

// whenever a msg is published to q, worker will be chosen to do calculation
// if 2 messages comes in, we can do parallel processing

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SQS {
    final Queue<ConsumerMessage> q;
    private final List<IConsumer> consumers;

    public SQS(){
        this.q = new LinkedList<>();
        this.consumers = new LinkedList<>();
    }

    public void publish(ConsumerMessage msg){
        synchronized (q){
            q.add(msg);
            q.notifyAll();
        }
    }

    public void registerConsumer(IConsumer consumer){
        consumers.add(consumer);

        // each thread is having its own Consumer Worker object which deals with its own work
        // since each worker is operating on same Q, we need to do synchronization at Q level
        new Thread(new ConsumerWorker(q, consumer)).start();
    }

    public void releaseAllWorkers(){
        synchronized (q){
            q.notifyAll();
        }
    }
}
