public class Board {

    public static final int SIZE = 15;
    Piece[][] arr;

    public Board(){
        arr = new Piece[15][15]; //Change to Element After, and once changed to type PIECE, every element will be null
       for (int i = 0; i<15; i++) {
            for(int j = 0; j<15; j++){
                arr[i][j]= new Piece(' ');
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

        for(int y = 0; y<15; y++){
            for(int x = 0; x<15; x++){
                output += " | " + arr[x][y].getLetter();
            }
            output += " |\n";
            for(int i = 0; i<32; i++){
                output += "__";
            }
            output += "\n\n";
        }

//        output += "Player 1 total score:" + player1Score + "\n";
        //       output += "Player 2 total score:" + player2Score + "\n";
        return output;
    }
/*
    public String[] getWords(int x, int y) {
            word potentialWords = new word();
            firstLetter fL = new firstLetter();
            potentialWords.x = "";
            potentialWords.y = "";
        //Check vertical word
        fL.x = x;
        fL.y = y;
        while (arr[fL.x][fL.y].getLetter() != ' ') {
            fL.y++;
        }
        fL.y--;
        while(arr[fL.x][fL.y].getLetter() != ' ') {
            potentialWords.y+=arr[fL.x][fL.y].getLetter();
            fL.y--;
        }
        potentialWords.y.toLowerCase();

        //Check horizontal word
        fL.x = pieceArr.get(0).x;
        fL.y = pieceArr.get(0).y;
        while (arr[fL.x][fL.y].getLetter() != ' ') {
            fL.x--;
        }
        fL.x++;
        while(arr[fL.x][fL.y].getLetter() != ' '){
            potentialWords.x +=arr[fL.x][fL.y].getLetter();
            fL.x++;
        }
        potentialWords.x.toLowerCase();


        if (potentialWords.y.length() == 1) {
            potentialWords.y = "";
        }
        if (potentialWords.x.length() == 1) {
            potentialWords.x = "";
        }

        wordarr[0] = potentialWords.x;
        wordarr[1] = potentialWords.y;
        return wordarr;
    }

*/

    public static void main(String args[]) {
        Board board1 = new Board();
        SelectionData d1 = new SelectionData("t_1_2");
        SelectionData d2 = new SelectionData("h_1_3");
        SelectionData d3 = new SelectionData("e_1_4");
        SelectionData d4 = new SelectionData("e_1_3");
        board1.placePiece(d1);
        board1.placePiece(d2);
        board1.placePiece(d3);
        board1.placePiece(d4);
        System.out.println(board1.toString());
        //board1.getWords(1,1 );
        //System.out.println("Horizontal Word: " + potentialWords.x);
        //System.out.println("Vertical Word: " + potentialWords.y);
    }
}
//hello
