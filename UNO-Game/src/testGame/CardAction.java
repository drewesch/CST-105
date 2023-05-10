package testGame;

import java.util.ArrayList;
import java.util.LinkedList;

// Interface for accessing Card Actions (for simplicity)
// This is an example of abstraction

public interface CardAction {
	
	public int isNormal(CardObject card, int playerNum);
	
	public int isSkip(CardObject card, int playerNum, int r);
	
	public int isReverse(CardObject card, int playerNum, int r);
	
	public int isDraw2(CardObject card, UnoDeck deck, ArrayList<PlayerObject> players, int playerNum, int r);
	
	public UnoColors isWild(int playerNum, LinkedList<CardObject> hand);
	
	public UnoColors isWildDraw4(UnoDeck deck, ArrayList<PlayerObject> players, LinkedList<CardObject> hand, int playerNum, int r);
	
}
