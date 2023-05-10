package testGame;

public enum UnoColors {
    // Return all colors, associate them with a char for simplicity
	
	NONE('N'), RED('R'), BLUE('B'), GREEN('G'), YELLOW('Y');

    private final char asChar;
    
    UnoColors(char asChar) {
        this.asChar = asChar;
    }
    
    public char asChar() {
        return asChar;
    }
}
