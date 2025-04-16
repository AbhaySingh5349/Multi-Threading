package GameLeaderboard.method1;

import GameLeaderboard.Player;

import java.util.*;

// "putIfAbsent" is not thread safe even it is a single statement
// if update comes in while or post sorting, results will vary (before sorting, create deep copy & sort it -> eventual consistency)

public class ReadTimeLeaderBoardService {
    private final Map<String, Player> playerMap;

    public ReadTimeLeaderBoardService() {
        this.playerMap = new HashMap<>();
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
    }

    // Eventually consistent leaderboard
    public List<Player> getTopKLeaderBoard(int k){
        List<Player> players = new ArrayList<>(playerMap.values());

        List<Player> copyPlayers = new ArrayList<>();
        for (Player player : players) copyPlayers.add(new Player(player.getId(), player.getScore()));
        Collections.sort(copyPlayers);

        return copyPlayers.subList(0, k);
    }
}
