package MultithreadingIssues.MemoryConsistency;

public class ReadWriteTask implements Runnable{
    private static volatile int data = 0; // "volatile" prevents thread from creating local copies of data

    private String type;

    public ReadWriteTask(String type){
        this.type = type;
    }

    @Override
    public void run() {
        if(type.equals("write")){
            while (data != 5){
                data++;
                System.out.println("Updated data value to: " + data + " by thread: " + Thread.currentThread().getName());

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }
        }else{
            int temp = 0; // copy of data
            while(true){
                if(temp != data){
                    temp = data;
                    System.out.println("New data value by temp as " + temp + " by thread: " + Thread.currentThread().getName());
                }
            }
        }
    }
}
