package SimpleQueueService;

// consumption we want to do is for 2 integers and sum them up

public class ConsumerMessage {
    Integer a;
    Integer b;

    public ConsumerMessage(Integer a, Integer b){
        this.a = a;
        this.b = b;
    }
}
