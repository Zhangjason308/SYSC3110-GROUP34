import java.io.Serializable;
import java.util.*;

public class Hand implements Serializable {//

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