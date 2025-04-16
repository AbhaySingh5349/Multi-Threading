package GameLeaderboard.method3;

import GameLeaderboard.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderBoardRankingWorker implements Runnable{
    private final LeaderBoardRankingState state;

    public LeaderBoardRankingWorker(LeaderBoardRankingState state) {
        this.state = state;
    }

    @Override
    public void run() {
        synchronized (state){
            while (state.getUpdateEventQueue().isEmpty()){
                try {
                    state.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            state.getUpdateEventQueue().poll();
        }

        List<Player> copyPlayers = new ArrayList<>();
        for (Player p : state.getPlayerMap().values()) copyPlayers.add(new Player(p.getId(), p.getScore()));
        Collections.sort(copyPlayers);

        state.setTopPlayerCache(copyPlayers.subList(0, state.getK()));
    }
}
