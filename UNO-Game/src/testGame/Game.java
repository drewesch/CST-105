package testGame;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

	static void beginRound(ArrayList<PlayerObject> players, int roundNumber) {
		// This is where rounds iterate
		System.out.printf("\nRound %d, Begin!%n", roundNumber);
		UnoDeck deck = new UnoDeck();
		deck.createDeck();
		deck.shuffle();
		// Choose Random Dealer if none already, else choose one next player
		int dealer = GameConditions.chooseDealer(players);
		System.out.printf("\nThis Round's Dealer: Player %d\n", dealer+1);

		// Initially disperse cards
		int playerOrder = 0;
		for (int i = 0; i < 7; i++) {
			for (Object p : players) {
				int dealerOrder = (dealer + playerOrder) % 4;
				deck.dealCard(players, dealerOrder);
				playerOrder++;
			}
		}

		// An example of waiting, a requirement for making this understandable
		GameConditions.wait(1.5);
		for (int i = 0; i < players.size(); i++) {
			displayHand(players, i);
		}
		
		// Create a separate discardDeck & add the first card from the deck to it
		LinkedList<CardObject> discardDeck = new LinkedList<CardObject>();
		deck.startCard(discardDeck);
		System.out.printf("Starting Card: %s\n", discardDeck.get(0).getDisplay());
		
		// Set starting variables
		// t = PlayerNum/Turn, r = Rotation, m = Movement, wildType = Color if WildCard is Played
		Boolean win = false;
		Boolean properStart = false;
		int t = dealer;
		int r = 1;
		int m = 1;
		UnoColors wildType = UnoColors.NONE;

		// Ensures an Official UNO Game Start
		while (properStart == false) {
			// If normal, do nothing
			if (discardDeck.get(0).getType() == UnoTypes.NORMAL) {
				properStart = true;
				break;
			// If Action Card, do that action card as if the dealer played it
			} else if (discardDeck.get(0).getType() == UnoTypes.SKIP) {
				System.out.println("Player " + (dealer+1) + " skipped Player " + (Math.floorMod((dealer+r), 4)+1) + "'s turn!");
				m = 2;
				properStart = true;
				break;
			} else if (discardDeck.get(0).getType() == UnoTypes.REVERSE) {
				System.out.printf("\nThe rotation switched from clockwise to counter-clockwise!\n");
				r = r*-1;
				properStart = true;
				break;
			} else if (discardDeck.get(0).getType() == UnoTypes.DRAW2) {
				m = 2;
				System.out.println("Player " + (dealer+1) + " forced Player " + (Math.floorMod((dealer+r), 4)+1) + " to draw four cards!");
				System.out.println("Player " + (dealer+1) + " skipped Player " + (Math.floorMod((dealer+r), 4)+1) + "'s turn!");
				deck.dealCard(players, (Math.floorMod((dealer+r), 4)));
				deck.dealCard(players, (Math.floorMod((dealer+r), 4)));
				properStart = true;
				break;
			// If WildCard, allow first player to decide
			} else if (discardDeck.get(0).getType() == UnoTypes.WILD) {
				System.out.println("Starting Player is deciding the starting color...");
				GameConditions.wait(1.5);
				PlayerObject player = players.get(dealer);
				LinkedList<CardObject> hand = player.getHand();
				wildType = GameConditions.chooseColor(hand);
				System.out.println("Player " + (Math.floorMod((dealer+r), 4)) + " chose the color " + wildType.toString() + "!");
				properStart = true;
				break;
			// If Wild Draw 4, restart the game and continue the loop for next starting card
			} else if (discardDeck.get(0).getType() == UnoTypes.WILDDRAW4) {
				System.out.println("\nStarting Card is a W+4, restarting deck...");
				GameConditions.wait(1.5);
				deck.replenish(discardDeck, true);
				deck.startCard(discardDeck);
				System.out.printf("\nNew Starting Card: %s", discardDeck.get(0).getDisplay());
			}
		}
		
		// Every loop of this simulates one turn
		while (win == false) {
			// Go to the proceeding turn according to previous rotation and movement
			t += r * m;
			// Using Math.floorMod to get the correct modulo, even if iterating backwards
			int playerNum = Math.floorMod(t, 4);
			// Display Turn Starting Conditions
			GameConditions.wait(1.5);
			System.out.printf("\nPlayer %d's turn!\n", playerNum+1);
			System.out.println("Top Card: " + discardDeck.get(0).getDisplay());
			if ((discardDeck.get(0).getType() == UnoTypes.WILD) || ((discardDeck.get(0).getType() == UnoTypes.WILDDRAW4))) {
				System.out.println("WC Color: " + wildType.toString());
			}
			System.out.println();
			displayHand(players, playerNum);
			
			// Create a play using the player, their hand, and previous variables
			PlayerObject player = players.get(playerNum);
			LinkedList<CardObject> handDeck = player.getHand();
			CardObject play = isMatch(deck, discardDeck, players, playerNum, wildType);
			
			// First check for no cards
			if (play == null) {
				GameConditions.wait(1.5);
				System.out.println("\nPlayer " + (playerNum+1) + " drew a card.");
				deck.dealCard(players, playerNum);
				play = isMatch(deck, discardDeck, players, playerNum, wildType);
			}
			
			// Else, make a play
			if (play != null) {
				GameConditions.wait(1.5);
				// Is this an uno?
				GameConditions.isUno(handDeck);
				// Clear the UnoColor if a previous card was played prior
				if ((play.getColor() != UnoColors.NONE) && (wildType != UnoColors.NONE)) {
					wildType = UnoColors.NONE;
				}
				
				// Create a new CardAction for the play, an interface for CardObject
				// Change the movement and rotation accordingly
				CardAction action = play;
				if (play.getType() == UnoTypes.NORMAL) {
					m = action.isNormal(play, playerNum);
				} else if (play.getType() == UnoTypes.SKIP) {
					m = action.isSkip(play, playerNum, r);
				} else if (play.getType() == UnoTypes.REVERSE) {
					r = action.isReverse(play, playerNum, r);
					m = 1;
				} else if (play.getType() == UnoTypes.DRAW2) {
					m = action.isDraw2(play, deck, players, playerNum, r);
				} else if (play.getType() == UnoTypes.WILD) {
					wildType = action.isWild(playerNum, handDeck);
					m = 1;
				} else {
					wildType = action.isWildDraw4(deck, players, handDeck, playerNum, r);
					m = 2;
				}
				discardDeck.add(0, play);
				handDeck.remove(play);
			} else {
				// If no card is played, reset movement to 1
				m = 1;
			}
			
			// Check if the player has no cards left after play
			for (PlayerObject p : players) {
				LinkedList<CardObject> hand = p.getHand();
				if (hand.size() == 0) {
					win = true;
					System.out.println(p.getName() + " wins!\n");
					// Iterate over players for points
					for (PlayerObject tp : players) {
						// Remove first player from this loop
						if (tp.equals(p)) {
							continue;
						}
						// Loop over all player's hands and give points according to Uno Rules
						LinkedList<CardObject> finalHand = tp.getHand();
						for (CardObject fc : finalHand) {
							p.setPoints(p.getPoints() + GameConditions.dispersePoints(fc));
						}
					}
					break;
				}
			}
		}
	}

	static void displayHand(ArrayList<PlayerObject> players, int playerNum) {
		// Reusable for any turn or player iterations
		String hand = "";
		// Grab the player from player ArrayList
		// Furthermore, grab hand ArrayList from player object
		// Then, grab the hand object at i from hand ArrayList
		// Finally, get the display and append to a string to display
		PlayerObject p = players.get(playerNum);
		LinkedList<CardObject> handDeck = p.getHand();
		for (int i = 0; i < handDeck.size(); i++) {
			CardObject handObject = handDeck.get(i);
			String handCard = handObject.getDisplay();
			if (i == (handDeck.size() - 1)) {
				hand += handCard;
			} else {
				hand += handCard + ", ";
			}
		}
		System.out.printf("Player %d's Hand: %s%n", (playerNum + 1), hand);
	}

	static CardObject isMatch(UnoDeck deck, LinkedList<CardObject> discardDeck, ArrayList<PlayerObject> players, int playerNum, UnoColors wildType) {
		// Crucial to detecting valid plays
		// Get Player deck and create temporary deck
		PlayerObject p = players.get(playerNum);
		LinkedList<CardObject> handDeck = p.getHand();
		ArrayList<CardObject> usableDeck = new ArrayList<CardObject>();
		for (int i = 0; i < handDeck.size(); i++) {
			CardObject handObject = handDeck.get(i);
			CardObject topCard = discardDeck.get(0);
			// If comparison results true, add it to the temporary deck
			if (cardCompare(handObject, topCard, wildType) == true) {
				usableDeck.add(handObject);
			}
		}

		if (usableDeck.size() == 0) {
			// If no cards, draw a card. If no cards, replenish the deck.
			if (deck.size() == 0) {
				System.out.println("\nReshuffling deck...");
				GameConditions.wait(1.5);
				deck.replenish(discardDeck, false);
			}
		} else {
			// Play a random card from the usableDeck
			int chooseCard = GameConditions.getRandomIntegerInRange(0, usableDeck.size()-1);
			CardObject play = usableDeck.get(chooseCard);
			return play;
		}
		return null;
	}
	

	public static Boolean cardCompare(CardObject o1, CardObject o2, UnoColors wildType) {
		// If wildType exists, set the card status to temporarily have it
		// This gets updated throughout the game, so no need to concern over breaking
		if (wildType != UnoColors.NONE) {
			CardObject tempstatus = o2;
			tempstatus.setColor(wildType);
			o2 = tempstatus;
		}
		// If it's a wild card, always return true
		// If there are colors, go over this loop
		// If colors match or values match, return true
		// For any other case, return true
		if ((o1.getType() == UnoTypes.WILD) || (o1.getType() == UnoTypes.WILDDRAW4)) {
			return true;
		} else if ((o1.getColor() != UnoColors.NONE) && (o2.getColor() != UnoColors.NONE)) {
			if (o1.getColor().equals(o2.getColor())) {
				return true;
			} else if ((o1.getValue() == o2.getValue())) {
				if (o1.getType() == UnoTypes.NORMAL) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		// This is for Wild Cards. They always can be played.
		} else {
			return true;
		}
	}
}
