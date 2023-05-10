package testGame;

import java.util.ArrayList;
import java.util.LinkedList;

public class CardObject implements CardAction {
	
	public CardObject(String display, UnoTypes type, UnoColors color, int value) {
		super();
		
		// set card parameters
		this.display = display;
		this.type = type;
		this.color = color;
		this.value = value;
	}

	// Card Object Variables
	private String display;
	private UnoTypes type;
	private UnoColors color;
	private int value;
	private static int cardCounter = 0;
	
	// Card Getters & Setters
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public UnoTypes getType() {
		return type;
	}
	public void setType(UnoTypes type) {
		this.type = type;
		cardCounter++;
	}
	public UnoColors getColor() {
		return color;
	}
	public void setColor(UnoColors color) {
		this.color = color;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public static int getCardCounter() {
		return cardCounter;
	}
	
	@Override
	public int isNormal(CardObject card, int playerNum) {
		// TODO Auto-generated method stub
		// Ensures movement is 1 (returned int)
		System.out.println("\nPlayer " + (playerNum+1) + " played a " + card.getDisplay() + " card!");
		return 1;
	}
	
	@Override
	public int isSkip(CardObject card, int playerNum, int r) {
		// TODO Auto-generated method stub
		// Changes movement to 2 (returned int)
		System.out.println("\nPlayer " + (playerNum+1) + " played a " + card.getDisplay() + " card!");
		System.out.println("Player " + (playerNum+1) + " skipped Player " + (Math.floorMod((playerNum+r), 4)+1) + "'s turn!");
		return 2;
	}
	
	@Override
	public int isReverse(CardObject card, int playerNum, int r) {
		// TODO Auto-generated method stub
		// Multiplies rotation by -1
		System.out.println("\nPlayer " + (playerNum+1) + " played a " + card.getDisplay() + " card!");
		r = r*-1;
		
		String previous;
		String rotation;
		if (r == 1) {
			previous = "counter-clockwise";
			rotation = "clockwise";
		} else {
			previous = "clockwise";
			rotation = "counter-clockwise";
		}
		
		System.out.printf("\nThe rotation switched from %s to %s!\n", previous, rotation);
		return r;
	}
	
	@Override
	public int isDraw2(CardObject card, UnoDeck deck, ArrayList<PlayerObject> players, int playerNum, int r) {
		// TODO Auto-generated method stub
		// Does Skip & activates dealCard() on next player twice
		System.out.println("\nPlayer " + (playerNum+1) + " played a " + card.getDisplay() + " card!");
		System.out.println("Player " + (playerNum+1) + " forced Player " + (Math.floorMod((playerNum+r), 4)+1) + " to draw two cards!");
		System.out.println("Player " + (playerNum+1) + " skipped Player " + (Math.floorMod((playerNum+r), 4)+1) + "'s turn!");
		deck.dealCard(players, (Math.floorMod((playerNum+r), 4)));
		deck.dealCard(players, (Math.floorMod((playerNum+r), 4)));
		return 2;
		
	}
	
	@Override
	public UnoColors isWild(int playerNum, LinkedList<CardObject> hand) {
		// TODO Auto-generated method stub
		System.out.println("\nPlayer " + (playerNum+1) + " played a Wild card!");
		
		// Activates Wild Card ability
		// Make a sensible random color choice
		UnoColors c = GameConditions.chooseColor(hand);
		System.out.println("Player " + (playerNum+1) + " chose the color " + c.toString() + "!");
		return c;
	}
	@Override
	public UnoColors isWildDraw4(UnoDeck deck, ArrayList<PlayerObject> players, LinkedList<CardObject> hand, int playerNum, int r) {
		// TODO Auto-generated method stub
		// Does Skip, activates dealCard() on next player 4 times
		// and activates wild card ability
		System.out.println("\nPlayer " + (playerNum+1) + " played a W+4 Card!");
		
		UnoColors c = GameConditions.chooseColor(hand);
		System.out.println("Player " + (playerNum+1) + " chose the color " + c.toString() + "!");
		
		// Same code from isDraw2()
		System.out.println("Player " + (playerNum+1) + " forced Player " + (Math.floorMod((playerNum+r), 4)+1) + " to draw four cards!");
		System.out.println("Player " + (playerNum+1) + " skipped Player " + (Math.floorMod((playerNum+r), 4)+1) + "'s turn!");
		deck.dealCard(players, (Math.floorMod((playerNum+r), 4)));
		deck.dealCard(players, (Math.floorMod((playerNum+r), 4)));
		deck.dealCard(players, (Math.floorMod((playerNum+r), 4)));
		deck.dealCard(players, (Math.floorMod((playerNum+r), 4)));
		return c;
	}
	
	// toString all objects
	@Override
	public String toString() {
		return "CardObject [display=" + display + ", type=" + type + ", color=" + color + ", value=" + value + "]";
	}
}
