package ChessInputDrill;

/**
 * Created with IntelliJ IDEA.
 * User: Kal Spass
 * Date: 17/07/13
 * Time: 23:25
 * To change this template use File | Settings | File Templates.
 */
public class EmptySpace extends ChessPieceBase {
    EmptySpace(boolean isWhite){super(isWhite);}
    public boolean isValidMove(int MoveToX,int MoveToY,int MoveFromX,int MoveFromY) {return false;}
    public boolean getColor() {
        return this.isWhite;
    }
    @Override
    public String toString() {
        return "EE";
    }
}
