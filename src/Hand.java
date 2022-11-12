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

    public void addPieces(ArrayList<Piece> arr) {
        if(sizeOfHand()<=7){  // check before pushing that this is an ok change
            for(Piece p : arr){
                this.pieces.add(p);
            }
        }
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



        /*
        for (int i = 0; i < arrOfInput.length; i++) {
            if (copy.contains(new Piece(arrOfInput[i]))) {
                count++;
                copy.remove(new Piece(arrOfInput[i]));
            }
        }
        if (count == arrOfInput.length) {
            return true;
        } else {
            return false;
        }
    }*/

    /*
       // String[] arrOfHand1 = (String[]) this.getHandPieces().toArray();
        char[] arrOfInput = str.toCharArray();
        ArrayList<Piece> copy = this.getHandPieces();

        //for (char c : arrOfInput) {  // hand:"a b c d e f g"       input: "help" -> char[] = h, e, l, p
            for (Piece p : this.getHandPieces()){
                if (copy.contains(p)){
                    copy.remove(p);

                }
                else{return false;}

            }
       // }
        return true;
/*
        for (char Input : copy) {

            int check = 0;
            for(Piece Han : this.getHandPieces()){
                if (Input == Character.toString(Han.getLetter())){

                    Input = "";
                    check = 1;
                }
            }
            if (check == 0){
                return false;
            }
        }
        return true; */

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