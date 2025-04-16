package GameLeaderboard.method3;

import GameLeaderboard.Player;

import java.util.*;

public class LeaderBoardRankingState {
    private final int k;
    private final Map<String, Player> playerMap;
    private boolean updateLeaderBoard;
    private List<Player> topPlayerCache;

    public LeaderBoardRankingState(int k) {
        this.k = k;
        this.updateLeaderBoard = false;
        this.playerMap = new HashMap<>();
        this.topPlayerCache = new ArrayList<>();
    }

    public int getK() {
        return k;
    }

    public boolean isUpdateLeaderBoard() {
        return updateLeaderBoard;
    }

    public Map<String, Player> getPlayerMap() {
        return playerMap;
    }

    public List<Player> getTopPlayerCache() {
        return topPlayerCache;
    }

    public void setUpdateLeaderBoard(boolean updateLeaderBoard) {
        this.updateLeaderBoard = updateLeaderBoard;
    }

    public void setTopPlayerCache(List<Player> topPlayerCache) {
        this.topPlayerCache = topPlayerCache;
    }
}
