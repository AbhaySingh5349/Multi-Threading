package GameLeaderboard.method2;

import GameLeaderboard.Player;

import java.util.*;

// for fast reads, we will store state change of order on every update
// efficient reads but slower writes as we are updating board synchronously for each score change

public class WriteTimeLeaderBoardService {
    private final int k;
    private final Map<String, Player> playerMap;
    private List<Player> topPlayerCache;

    public WriteTimeLeaderBoardService(int k) {
        this.k = k;
        this.playerMap = new HashMap<>();
        this.topPlayerCache = new ArrayList<>();
    }

    public void updateScore(String playerId, Integer score){
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

        List<Player> copyPlayers = new ArrayList<>();
        for (Player p : playerMap.values()) copyPlayers.add(new Player(p.getId(), p.getScore()));
        Collections.sort(copyPlayers);

        this.topPlayerCache = copyPlayers.subList(0, k);
    }

    public List<Player> getTopKLeaderBoard(){
        return this.topPlayerCache;
    }
}
