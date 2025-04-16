package GameLeaderboard.method3;

import GameLeaderboard.Player;

import java.util.*;

// storing update events for each player & processing them asynchronously

public class AsyncLeaderBoardService {
    private final int k;
    private final LeaderBoardRankingState state;

    public AsyncLeaderBoardService(int k) {
        this.k = k;
        this.state = new LeaderBoardRankingState(k);
        new Thread(new LeaderBoardRankingWorker(state)).start();
    }

    public void updateScore(String playerId, Integer score){
        Map<String, Player> playerMap = state.getPlayerMap();

        // double checking
        if(!playerMap.containsKey(playerId)){
            synchronized (playerMap){
                if(!playerMap.containsKey(playerId)){
                    playerMap.put(playerId, new Player(playerId, 0));
                }
            }
        }

        Player player = playerMap.get(playerId);
        // only issue is we are taking too many locks (lock overhead is here, so we will optimize by taking group locks)
        synchronized (player){
            player.setScore(player.getScore()+score);
        }

        synchronized (state){
            state.setUpdateLeaderBoard(true);
            state.notifyAll();
        }
    }

    public List<Player> getTopKLeaderBoard(){
        return state.getTopPlayerCache();
    }
}
