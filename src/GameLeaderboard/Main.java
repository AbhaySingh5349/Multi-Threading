package GameLeaderboard;

import GameLeaderboard.method3.AsyncLeaderBoardService;

public class Main {
    public static void main(String[] args) {
        final AsyncLeaderBoardService leaderBoardService = new AsyncLeaderBoardService(3);

        leaderBoardService.updateScore("p1", 1);
        leaderBoardService.updateScore("p2", 3);
        leaderBoardService.updateScore("p3", 2);
        leaderBoardService.updateScore("p4", 5);
        leaderBoardService.updateScore("p5", 4);
        leaderBoardService.updateScore("p3", 2);

        try {
            Thread.sleep(1000); // waiting for worker to compute results
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leaderBoardService.getTopKLeaderBoard().forEach(System.out::println);
    }
}
