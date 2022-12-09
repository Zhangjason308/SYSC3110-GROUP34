import java.io.Serializable;
import java.util.HashMap;
public class Piece implements Serializable {
    char piece;
    public static final HashMap<Character, Integer> pieceMap = new HashMap<>();
    static {
        pieceMap.put('e', 1);
        pieceMap.put('a', 1);
        pieceMap.put('i', 1);
        pieceMap.put('o', 1);
        pieceMap.put('n', 1);
        pieceMap.put('r', 1);
        pieceMap.put('t', 1);
        pieceMap.put('l', 1);
        pieceMap.put('s', 1);
        pieceMap.put('u', 1);
        pieceMap.put('d', 2);
        pieceMap.put('g', 2);
        pieceMap.put('b', 3);
        pieceMap.put('c', 3);
        pieceMap.put('m', 3);
        pieceMap.put('p', 3);
        pieceMap.put('f', 4);
        pieceMap.put('h', 4);
        pieceMap.put('v', 4);
        pieceMap.put('w', 4);
        pieceMap.put('y', 4);
        pieceMap.put('k', 5);
        pieceMap.put('j', 8);
        pieceMap.put('x', 8);
        pieceMap.put('q', 10);
        pieceMap.put('z', 10);
    }
    /**
     *
     * @param piece
     */
    public Piece(char piece){
        this.piece = piece;
    }


    public char getLetter(){

        return piece;
    }

    public void setLetter(char c){
        piece = c;
    }

}
