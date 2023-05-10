package testGame;

public enum UnoTypes {
	// Associate all types with a simplified shortcut string
	// Make "normal" visible in console, but not displayed since it is inferred
	// Defined in UnoDeck Class
	
	NORMAL("Normal"), SKIP("SK"), REVERSE("RV"), DRAW2("+2"), WILD("WC"), WILDDRAW4("W+4");
    
    private final String asString;
    
	UnoTypes(String asString) {
        this.asString = asString;
    }
    
    public String asString() {
        return asString;
    }
}
