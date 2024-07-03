package MessageBroker;

import SimpleQueueService.AdditionConsumer;
import SimpleQueueService.ConsumerMessage;
import SimpleQueueService.SubtractionConsumber;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final MB mb = new MB();

        AdditionConsumer ac1 = new AdditionConsumer();
//        AdditionConsumer ac2 = new AdditionConsumer();

        SubtractionConsumber sc1 = new SubtractionConsumber();
        SubtractionConsumber sc2 = new SubtractionConsumber();

        mb.register("addition", Arrays.asList(ac1));
        mb.register("subtraction", Arrays.asList(sc1, sc2));

        mb.publish("addition", new ConsumerMessage(1,2 ));
        mb.publish("addition", new ConsumerMessage(3,4 ));
        mb.publish("subtraction", new ConsumerMessage(2,3 ));
        mb.publish("subtraction",new ConsumerMessage(4,5 ));
    }
}
