package ChessInputDrill;

/**
 * Created with IntelliJ IDEA.
 * User: Kal Spass
 * Date: 16/07/13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class BishopPiece extends ChessPieceBase {
    BishopPiece(boolean isWhite) { super(isWhite);}

    @java.lang.Override
    public boolean isValidMove(int MoveToX,int MoveToY,int MoveFromX,int MoveFromY) {
        if ((MoveFromX-MoveToX)==(MoveFromY-MoveToY)||(MoveFromX-MoveToX)==((MoveFromY-MoveToY)*(-1)))
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
        return  "WB";
        else
            return "BB";
    }
}
