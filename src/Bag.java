import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Bag implements Serializable {

    final static char[] ALPHABET = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!'};
    final static int[] NUMBER_OF_EACH = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1, 2};
    ArrayList<Piece> bagPieces;

    public Bag(){
        bagPieces = new ArrayList<Piece>();
        ArrayList<Piece> uniquePieces = new ArrayList<Piece>();
        int i = 0;
        for(char c : ALPHABET){  // makes each letter in the alphabet into a piece and add it to an arrayList
            uniquePieces.add(new Piece(c));
            i++;
        }
        i = 0;
        for (Piece p : uniquePieces) {  // adds the appropriate amount of each letter as a piece to the letter bag
            int numToAdd = NUMBER_OF_EACH[i];
            while(numToAdd > 0){
                bagPieces.add(uniquePieces.get(i));
                numToAdd--;
            }
            i++;
        }
        // Collections.shuffle(letterBag); the grab function pulls from a random index so randomizing the bag can be optional
    }

    /**
     * @return
     */
    public int numberOfRemainingPieces(){
        return bagPieces.size();
    }

    public ArrayList<Piece> getBagPieces(){
        return bagPieces;
    }


    public ArrayList<Piece> grabPieces(int number){  //returns a random piece and removes that piece from this bag

        Random random = new Random();
        ArrayList<Piece> pieces = new ArrayList<Piece>();

        for (int i =0; i < number; i++){
            int randIndex = random.nextInt(numberOfRemainingPieces());
            Piece toReturn = bagPieces.get(randIndex);
            pieces.add(toReturn);
            bagPieces.remove(randIndex);
        }
        return pieces;
    }


    public String toString(){
        ArrayList<Piece> letters = bagPieces;
        String bagString = "";
        Piece lastPiece = null;
        int numOf = 0;
        int i = 0;
        for (Piece p : letters) {
            if(lastPiece == null){
                numOf++;
                lastPiece = p;
                continue;
            }
            if(i == letters.size()-1) {
                if (lastPiece.getLetter() == p.getLetter()) {
                    numOf++;
                    bagString += lastPiece.getLetter() + ":" + numOf + " ";
                } else {
                    bagString += lastPiece.getLetter() + ":" + numOf + " ";
                    bagString += p.getLetter() + ":1";
                }
            }
            else{
                if (lastPiece.getLetter() == p.getLetter()) {
                    numOf++;
                } else {
                    bagString += lastPiece.getLetter() + ":" + numOf + " ";
                    numOf = 1;
                }
                lastPiece = p;
            }

            i++;
        }
        return bagString;
    }
}
