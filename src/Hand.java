import java.util.*;

public class Hand{

    ArrayList<Piece> pieces;

    public Hand(){
        pieces = new ArrayList<Piece>(7);
    }

    public int sizeOfHand(){
        return this.pieces.size();
    }

    public void addPiece(Piece piece){
        if(pieces.size() < 7){
            this.pieces.add(piece);
        }
    }
    public ArrayList<Piece> getHandPieces(){
        return this.pieces;
    }

    public void setHandPieces(ArrayList<Piece> setTo){pieces = setTo;}
    public void addPieces(ArrayList<Piece> arr) {
        if(sizeOfHand()<7){  // check before pushing that this is an ok change
            for(Piece p : arr){
                this.pieces.add(p);
            }
        }
    }

    public void changeBlankValue(int index, char newChar){
        if(pieces.get(index).getLetter() == '!'){
            pieces.get(index).setLetter(newChar);
        }
    }

    public ArrayList<Character> getHandAsChars(){
        ArrayList<Character> chars = new ArrayList<>();
        for (Piece p : pieces) {
            chars.add(p.getLetter());
        }
        return chars;
    }

    public Piece getPieceWithChar(char c){
        for (int i = 0; i < sizeOfHand(); i++){
            if(c == pieces.get(i).getLetter()){
                return  pieces.get(i);
            }
        }
        System.out.println("getIndexOfPieceWidth() returned a value of -1; the hand doesnt contain that char");
        return null;
    }

    public boolean containsLetters(String str) {

        int count = 0;
        char[] arrOfInput = str.toCharArray();
        ArrayList<Piece> arrOfPiece = new ArrayList<Piece>();

        for (char c : arrOfInput) {
            arrOfPiece.add(new Piece(c));
        }

        ArrayList<Piece> copy = this.getHandPieces();

        for (Piece ap : arrOfPiece) {
            if (copy.contains(ap)) {
                copy.remove(ap);
            } else {
                return false;
            }
        }

        return true;
    }
    public Piece removePiece(int i){
        return pieces.remove(i);
    }

    public String toString(){
        String out = "";
        for (Piece p : pieces){
            out += p.getLetter();
        }
        return out;
    }

}