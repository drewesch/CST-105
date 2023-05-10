package testGame;

import java.util.LinkedList;

public class PlayerObject {
	
	public PlayerObject() {
		super();
		
		// set player parameters
		name = "";
		dealer = false;
		points = 0;
	}
	
	// Player Object Variables
	private String name;
	private LinkedList<CardObject> hand = new LinkedList<CardObject>();
	private boolean dealer;
	private int points;
	
	// Player Getters & Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<CardObject> getHand() {
		return hand;
	}
	public void setHand(LinkedList<CardObject> hand) {
		this.hand = hand;
	}
	public boolean isDealer() {
		return dealer;
	}
	public void setDealer(boolean dealer) {
		this.dealer = dealer;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	// toString every object
	@Override
	public String toString() {
		return "PlayerObject [name=" + name + ", hand=" + hand + ", dealer=" + dealer + ", points=" + points + "]";
	}
}
