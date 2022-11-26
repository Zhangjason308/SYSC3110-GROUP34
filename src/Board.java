public class Board {//

    public static final int SIZE = 15;
    Piece[][] arr;

    public Board(){
        arr = new Piece[15][15]; //Change to Element After, and once changed to type PIECE, every element will be null
        for (int i = 0; i<15; i++) {
            for(int j = 0; j<15; j++){
                arr[j][i]= new Piece(' ');
            }
        }
    }


    public Piece getPiece(int x, int y) {
        return arr[x][y];
    }

    public void placePiece(SelectionData d) {
        if(arr[d.getX()][d.getY()].getLetter() == (' ')){
            arr[d.getX()][d.getY()] = d.getPiece();
        }
    }

    public Piece removePiece(int x, int y){
        Piece p = arr[x][y];
        arr[x][y] = new Piece(' ');
        return p;
    }

    public String toString(){  // this function works as is
        String output = "\n\n\t\t\t\t\t\tSCRABBLE BOARD\n";

        for(int x = 0; x<15; x++){
            for(int y = 0; y<15; y++){
                output += " | " + arr[y][x].getLetter();
            }
            output += " |\n";
            for(int i = 0; i<32; i++){
                output += "__";
            }
            output += "\n\n";
        }
        return output;
    }
}

