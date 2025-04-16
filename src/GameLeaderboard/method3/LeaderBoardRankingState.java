package GameLeaderboard.method3;

import GameLeaderboard.Player;

import java.util.*;

public class LeaderBoardRankingState {
    private final int k;
    private final Map<String, Player> playerMap;
    private final Queue<Player> updateEventQueue;
    private List<Player> topPlayerCache;

    public LeaderBoardRankingState(int k) {
        this.k = k;
        this.updateEventQueue = new LinkedList<>();
        this.playerMap = new HashMap<>();
        this.topPlayerCache = new ArrayList<>();
    }

    public int getK() {
        return k;
    }

    public Queue<Player> getUpdateEventQueue() {
        return updateEventQueue;
    }

    public Map<String, Player> getPlayerMap() {
        return playerMap;
    }

    public List<Player> getTopPlayerCache() {
        return topPlayerCache;
    }

    public void setTopPlayerCache(List<Player> topPlayerCache) {
        this.topPlayerCache = topPlayerCache;
    }
}
