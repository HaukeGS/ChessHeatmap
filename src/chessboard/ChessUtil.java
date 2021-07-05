package chessboard;

public class ChessUtil {
	
	public static int asciiOffset(char x) {
		int temp = (int)x;
		return temp - 97;
	}
	
	public static int inverseIndex(int y) {
		return 8-y;
	}

}
