public class SelectionData {
    private int x;
    private int y;
    private Piece p;

    public SelectionData(String input){// input is in the form t_1_11     put t_1_2,h_1_2,e_12,13
        String[] str = input.split("_");
        p = new Piece(str[0].charAt(0));
        x = Integer.valueOf(str[1]);
        y = Integer.valueOf(str[2]);
    }

    public SelectionData(int x, int y, Piece p){// input is in the form t_1_11     put t_1_2,h_1_2,e_12,13
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public Piece getPiece(){
        return p;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public static void main(String args[]) {
        SelectionData d = new SelectionData("t_11_12");
        System.out.println(d.getX());
        System.out.println(d.getY());
        System.out.println(d.getPiece().getLetter());
    }
}
