import java.util.HashMap;
public class Piece {
    char piece;
    private static final HashMap<Character, Integer> pieceMap = new HashMap<>();
    static {
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

    public boolean equals(Piece p){
        if (this.getLetter() ==  p.getLetter()) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        Piece p = new Piece('Q');
        Piece p1 = new Piece('Q');
        if (p.equals(p1)) {
            System.out.println(p.getLetter());
        }
        //System.out.println("SCORE for p: "+ pieceMap.get('A'));
        //System.out.println("SCORE for p1: "+ pieceMap.get('Z'));

    }


}
