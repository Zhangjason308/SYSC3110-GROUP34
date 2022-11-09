import java.util.HashMap;
public class Piece {
    char piece;
    private static final HashMap<Character, Integer> pieceMap = new HashMap<>();
    static {
        /*
        myMap.put(new Piece('E'), 1);
        myMap.put(new Piece('A'), 1);
        myMap.put(new Piece('O'), 1);
        myMap.put(new Piece('N'), 1);
        myMap.put(new Piece('R'), 1);
        myMap.put(new Piece('T'), 1);
        myMap.put(new Piece('L'), 1);
        myMap.put(new Piece('S'), 1);
        myMap.put(new Piece('U'), 1);
        myMap.put(new Piece('D'), 2);
        myMap.put(new Piece('G'), 2);
        myMap.put(new Piece('B'), 3);
        myMap.put(new Piece('C'), 3);
        myMap.put(new Piece('M'), 3);
        myMap.put(new Piece('P'), 3);
        myMap.put(new Piece('F'), 4);
        myMap.put(new Piece('H'), 4);
        myMap.put(new Piece('V'), 4);
        myMap.put(new Piece('W'), 4);
        myMap.put(new Piece('Y'), 4);
        myMap.put(new Piece('K'), 5);
        myMap.put(new Piece('J'), 8);
        myMap.put(new Piece('X'), 8);
        myMap.put(new Piece('Q'), 10);
        myMap.put(new Piece('Z'), 10);
         */

        pieceMap.put('E', 1);
        pieceMap.put('A', 1);
        pieceMap.put('O', 1);
        pieceMap.put('N', 1);
        pieceMap.put('R', 1);
        pieceMap.put('T', 1);
        pieceMap.put('L', 1);
        pieceMap.put('S', 1);
        pieceMap.put('U', 1);
        pieceMap.put('D', 2);
        pieceMap.put('G', 2);
        pieceMap.put('B', 3);
        pieceMap.put('C', 3);
        pieceMap.put('M', 3);
        pieceMap.put('P', 3);
        pieceMap.put('F', 4);
        pieceMap.put('H', 4);
        pieceMap.put('V', 4);
        pieceMap.put('W', 4);
        pieceMap.put('Y', 4);
        pieceMap.put('K', 5);
        pieceMap.put('J', 8);
        pieceMap.put('X', 8);
        pieceMap.put('Q', 10);
        pieceMap.put('Z', 10);

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


    public static void main(String args[]) {
        Piece p = new Piece('Q');
        Piece p1 = new Piece('A');
        System.out.println(p.getLetter());
        System.out.println("SCORE for p: "+ pieceMap.get('A'));
        System.out.println("SCORE for p1: "+ pieceMap.get('Z'));

    }
}
