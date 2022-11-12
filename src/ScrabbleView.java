public interface ScrabbleView {
    public void updateHandFrame(int x, int y, Piece selectedPiece); //make it one update and take in parameter(ScrabbleGame)
    public void updateInfoPanel(int p1, int p2, Bag baggy);
}