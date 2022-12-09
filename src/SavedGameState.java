import java.io.Serializable;

public class SavedGameState implements Serializable {

    private int player1Score;
    private int player2Score;
    private Hand player1Hand = new Hand();
    private Hand player2Hand = new Hand();
    private Board scrabbleBoard = new Board();
    private Bag bag = new Bag();
    private boolean turn;
    SelectionController selectionController;

    public SavedGameState(ScrabbleGame game){
        bag.grabPieces(100);
        player1Score = game.getPlayer1Score();
        player2Score = game.getPlayer2Score();
        for (int i = 0 ; i < game.getPlayer1Hand().getHandPieces().size(); i++) {
            player1Hand.getHandPieces().add(new Piece(game.getPlayer1Hand().getHandPieces().get(i).getLetter()));
            player2Hand.getHandPieces().add(new Piece(game.getPlayer2Hand().getHandPieces().get(i).getLetter()));
        }
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                scrabbleBoard.getBoard()[i][j] = game.getBoard().getPiece(i,j);
            }
        }
        for (int i = 0; i < game.getBag().getBagPieces().size(); i++) {
            bag.getBagPieces().add(game.getBag().getBagPieces().get(i));
        }
        turn =game.getTurn();
        selectionController = game.getSelectionController();
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    public Board getScrabbleBoard() {
        return scrabbleBoard;
    }

    public Bag getBag() {
        return bag;
    }

    public boolean getTurn() {
        return turn;
    }

    public SelectionController getSelectionController() {
        return selectionController;
    }

    public String toString() {
        String gameState = "";
        gameState += "Player 1 Turn? :" + getTurn() + "\n";
        gameState += "-----------------------------------------\n";
        gameState += "Player 1 Hand :" + getPlayer1Hand() + "\n";
        gameState += "Player 2 Hand :" + getPlayer2Hand() + "\n";
        gameState += "Player 1 Score :" + getPlayer1Score() + "\n";
        gameState += "Player 2 Score :" + getPlayer2Score() + "\n";
        gameState += "Bag Pieces :" + getBag().getBagPieces().size() + "\n";

        return gameState;
    }
}
