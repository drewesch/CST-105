package testGame;

import java.util.ArrayList;
import testGame.Game;

public class UnoDriver {
	
	private static final int playerCount = 4;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to the UNO Game Simulator!");
		ArrayList<PlayerObject> players = createPlayers(playerCount);
		iterateRounds(players);
	}
	
	static ArrayList<PlayerObject> createPlayers(int num) {
		// Create Player ArrayList (of 4)
		ArrayList<PlayerObject> players = new ArrayList<PlayerObject>();
		for (int i = 0; i < num; i++) {
			PlayerObject p = new PlayerObject();
			p.setName(String.format("Player %d", (i+1)));
			players.add(p);
		}
		return players;
	}
	
	static void iterateRounds(ArrayList<PlayerObject> players) {
		// Check for winner, else continue rounds
		Boolean win = false;
		// Iterate Rounds
		int roundNumber = 0;
		while (win == false) {
			if (win != true) {
				roundNumber++;
				Game.beginRound(players, roundNumber);
			}
			System.out.println("\nScoreboard:");
			// Print Player Score-board & Check for Winner Every Round
			for (int i = 0; i < playerCount; i++) {
				PlayerObject p = players.get(i);
				System.out.println(p.getName() + ": " + p.getPoints());
				// Clear Hand
				p.getHand().clear();
				if (p.getPoints() > 499) {
					GameConditions.wait(1.5);
					System.out.printf("\n" + p.getName() + " is the winner with %d points!", p.getPoints());
					win = true;
				}
			}
		}
	}
}
