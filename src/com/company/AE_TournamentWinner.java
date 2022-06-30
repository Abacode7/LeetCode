package com.company;

import java.util.*;

public class AE_TournamentWinner {
    public String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        Map<String, Integer> map = new HashMap<>();
        String maxTeam = "";
        int maxScore = Integer.MIN_VALUE;

        for(int i=0; i<results.size(); i++){
            ArrayList<String> teams = competitions.get(i);
            int result = results.get(i);

            map.putIfAbsent(teams.get(0), new Integer(0));
            map.putIfAbsent(teams.get(1), new Integer(0));

            String scoringTeam;
            if(result == 1) scoringTeam = teams.get(0);
            else scoringTeam = teams.get(1);

            int currentScore = map.get(scoringTeam);
            map.put(scoringTeam, currentScore + 3);

            if(map.get(scoringTeam) > maxScore){
                maxTeam = scoringTeam;
                maxScore = map.get(scoringTeam);
            }
        }

        // Write your code here.
        return maxTeam;
    }
}
