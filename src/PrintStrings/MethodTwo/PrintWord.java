package PrintStrings.MethodTwo;

import PrintStrings.MethodTwo.PrintState;

public class PrintWord implements Runnable{
    public final PrintState state;
    public String word;
    public String nextWord;

    public PrintWord(PrintState state, String word, String nextWord){
        this.state = state;
        this.word = word;
        this.nextWord = nextWord;
    }

    @Override
    public void run() {
        synchronized (state){
            while(!state.nextWord.equals(word)){
                try {
                    state.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(word + " " +  Thread.currentThread().getName());

            state.nextWord = nextWord;
            state.notifyAll();
        }
    }
}
