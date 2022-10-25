public class Piece {
    char piece;

    /**
     *
     * @param piece
     */
    public Piece(char piece){
        this.piece = piece;
    }

    /**
     *
     * @return Returns the points of the entered character.
     */
    public int piecePoint(){

        if (piece == 'E'){
            return 1;
        } else if (piece == 'A'){
            return 1;
        } else if (piece == 'I'){
            return 1;
        } else if (piece == 'O'){
            return 1;
        } else if (piece == 'N'){
            return 1;
        } else if (piece == 'R'){
            return 1;
        } else if (piece == 'T'){
            return 1;
        } else if (piece == 'L'){
            return 1;
        } else if (piece == 'S'){
            return 1;
        } else if (piece == 'U'){
            return 1;
        } else if (piece == 'D'){
            return 2;
        } else if (piece == 'G'){
            return 2;
        } else if (piece == 'B'){
            return 3;
        } else if (piece == 'C'){
            return 3;
        } else if (piece == 'M'){
            return 3;
        } else if (piece == 'P'){
            return 3;
        } else if (piece == 'F'){
            return 4;
        } else if (piece == 'H'){
            return 4;
        } else if (piece == 'V'){
            return 4;
        } else if (piece == 'W'){
            return 4;
        } else if (piece == 'Y'){
            return 4;
        } else if (piece == 'K'){
            return 5;
        } else if (piece == 'J'){
            return 8;
        } else if (piece == 'X'){
            return 8;
        } else if (piece == 'Q'){
            return 10;
        } else if (piece == 'Z'){
            return 10;
        }
        else return 0;
    }

    public char getLetter(){

        return piece;
    }


    public static void main(String args[]) {
        Piece p = new Piece('Q');
        Piece p1 = new Piece('A');
        System.out.println(p.getLetter());
        System.out.println("SCORE for p: "+ p.piecePoint());
        System.out.println("SCORE for p1: "+p1.piecePoint());

    }
}
