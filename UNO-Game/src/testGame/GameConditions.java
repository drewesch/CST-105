package testGame;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameConditions {
	
	static int chooseDealer(ArrayList<PlayerObject> players) {
		int dealer = 0;
		for (int i = 0; i < players.size(); i++) {
			// If this player has dealer = true, set next player to dealer (clockwise order)
			// If no dealer exists, choose a player randomly
			PlayerObject p = players.get(i);
			PlayerObject np = players.get((i+1)%4);
			int npn = (i+1)%3;
			if (p.isDealer() == true) {
				p.setDealer(false);
				np.setDealer(true);
				dealer = npn;
			} else if (i == 3 && p.isDealer() == false) {
				dealer = getRandomIntegerInRange(0,3);
			}
		}
		return dealer;
	}
	
	static int getRandomIntegerInRange(int min, int max) {
		// Randomizer between inputed range
		int x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x;
	}
	
	static void wait(double seconds) {
		try {
			// Interrupt for x time converted to milliseconds
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	static int dispersePoints(CardObject card) {
		// Based on the Official Uno Rules
		// Normals = Their Value
		// Skips, Reverses, and Draw 2 = 20 points
		// Wild & Wild Draw 4 = 50 points
		if (card.getType() == UnoTypes.NORMAL) {
			return card.getValue();
		} else if ((card.getType() == UnoTypes.WILD) || (card.getType() == UnoTypes.WILDDRAW4)) {
			return 50;
		} else {
			return 20;
		}
	}
	
	
	static Boolean isUno(LinkedList<CardObject> hand) {
		// If Hand Size is 2, print "UNO!"
		if (hand.size() == 2) {
			System.out.println("UNO!");
			return true;
			
		} else {
			return false;
		}
	}

	static UnoColors chooseColor(LinkedList<CardObject> hand) {
		// TODO Auto-generated method stub
		// Chooses a random feasible color from player hand
		// Then, returns a random color from that list
		LinkedList<UnoColors> chooseList = new LinkedList<UnoColors>();
			for (CardObject card : hand) {
				if (card.getColor() != UnoColors.NONE) {
					if (chooseList.contains(card.getColor()) == false) {
						chooseList.add(card.getColor());
					}
				}
			}
		UnoColors c = chooseList.get(GameConditions.getRandomIntegerInRange(0, chooseList.size()-1));
		return c;
	}
}
