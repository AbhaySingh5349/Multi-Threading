package OddEven.MethodTwo;

public class PrintState {
    public int curNum;
    public Turn curTurn;

    public PrintState(Turn turn, int num){
        this.curNum = num;
        this.curTurn = turn;
    }
}
