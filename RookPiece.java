package ChessInputDrill;

/**
 * Created with IntelliJ IDEA.
 * User: Kal Spass
 * Date: 16/07/13
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class RookPiece extends ChessPieceBase {
    public RookPiece(boolean Color) {super(Color);}
    protected boolean Color;
    boolean HasMoved=false;
    public boolean PieceHasMoved (){
        return this.HasMoved;
    }
    @java.lang.Override
    public boolean isValidMove(int MoveToX,int MoveToY,int MoveFromX,int MoveFromY) {
        if ((MoveFromX-MoveToX)==0||(MoveFromY-MoveToY)==0)
            return true;
        else
        return false;
    }
    public boolean getColor() {
        return this.isWhite;
    }
    @Override
    public String toString() {
        if (isWhite)
            return  "WR";
        else
            return "BR";
    }
}
