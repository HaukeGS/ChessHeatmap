package chessutility;

public class ChessUtil {
	
	public static int asciiOffset(char x) {
		return (int) (x - 97);
	}
	
	public static char asciiOffset(int x) {
		return (char) (x + 97);
	}
	
	public static int inverseIndex(int y) {
		return 8-y;
	}

}
