package MessageBroker;

import SimpleQueueService.ConsumerMessage;
import SimpleQueueService.IConsumer;
import SimpleQueueService.SQS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// broker can have multiple topics
// topic will have multiple queues
// client will give subscriber i.e list of consumers

public class MB {
    private final Map<String, SQS> topicSqsQueues;

    public MB(){
        this.topicSqsQueues = new HashMap<>();
    }

    public void register(String subscriptionName, List<IConsumer> consumers){
        topicSqsQueues.putIfAbsent(subscriptionName, new SQS());

        SQS sqs = topicSqsQueues.get(subscriptionName);
        for (IConsumer consumer : consumers) {
            sqs.registerConsumer(consumer);
        }
    }

    public void publish(String subscriptionName, ConsumerMessage msg){
        SQS sqs = topicSqsQueues.get(subscriptionName);

        System.out.println("Message published to " + subscriptionName);
        sqs.publish(msg);

        /*
        // to publish msg to all queues
        for(SQS sqs : topicSqsQueues.values()){
            sqs.publish(msg);
        }
         */
    }
}
