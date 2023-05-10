package testGame;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

import testGame.CardObject;

public class UnoDeck {

	public UnoDeck() {
		super();
		deck = new LinkedList<CardObject>();
	}

	private static LinkedList<CardObject> deck;

	public void createDeck() {
		// d = display, t = card type, c = color type, v = value
		int v = -1;
		String d = "";
		for (UnoTypes t : UnoTypes.values()) {
			// set type
			// set normal, set color, set value
			if (t.asString() == "Normal") {
				for (UnoColors c : UnoColors.values()) {
					if (c.asChar() == 'N') {
						continue;
					}
					for (v = 0; v < 10; v++) {
						// set value (0-9)
						if (v == 0) {
							// make one card
							d = String.format("%c%d", c.asChar(), v);
							deck.add(new CardObject(d, t, c, v));
						} else {
							// make two cards
							d = String.format("%c%d", c.asChar(), v);
							deck.add(new CardObject(d, t, c, v));
							deck.add(new CardObject(d, t, c, v));
						}
					}
				}
			} else if ((t.asString() == "SK") || (t.asString() == "RV") || (t.asString() == "+2")) {
				// skip, reverse, and draw 2; set color but no value
				// make two of each
				for (UnoColors c : UnoColors.values()) {
					// set color
					if (c.asChar() == 'N') {
						continue;
					}
					d = String.format("%c%s", c.asChar(), t.asString());
					deck.add(new CardObject(d, t, c, v));
					deck.add(new CardObject(d, t, c, v));
				}
			} else if ((t.asString() == "WC") || (t.asString() == "W+4")) {
				// wild and wild draw 4; set no color & no value
				// make four of each
				UnoColors c = UnoColors.NONE;
				d = String.format("%s", t.asString());
				deck.add(new CardObject(d, t, c, v));
				deck.add(new CardObject(d, t, c, v));
				deck.add(new CardObject(d, t, c, v));
				deck.add(new CardObject(d, t, c, v));
			}
		}
	}

	public void shuffle() {
		// Sudo-random shuffle of ArrayList
		Collections.shuffle(deck);
	}
	
	public void startCard(LinkedList<CardObject> discardDeck) {
		// Take the first card of the created deck and add it to the discardDeck
		CardObject topCard = deck.get(0);
		discardDeck.add(topCard);
		deck.remove(topCard);
	}
	
	public void dealCard(ArrayList<PlayerObject> players, int playerNumber) {
		// Deal top card, then move towards discard pile
		PlayerObject p = players.get(playerNumber);
		CardObject topCard = deck.get(0);
		p.getHand().add(topCard);
		// Make this go to discard deck in the future
		deck.remove(0);
	}
	
	public void replenish(LinkedList<CardObject> discardDeck, Boolean change) {
		// Take from discardDeck and add it all (except topCard) to playable deck
		// Then shuffle the playable deck
		
		// When starting condition requires first card removal, start iteration at 0.
		// Else, start iteration at 1 to keep the top card.
		int i;
		if (change == true) {
			i = 0;
		} else {
			i = 1;
		}
		
		for (int j = i; i < discardDeck.size(); i++) {
			CardObject card = discardDeck.get(j);
			deck.add(card);
			discardDeck.remove(card);
		}
		shuffle();
	}
	
	// Only for one function where calling the size is necessary
	// Might remove this later if possible
	public int size() {
		return deck.size();
	}
}
