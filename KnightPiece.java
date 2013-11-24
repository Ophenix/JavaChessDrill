package ChessInputDrill;

/**
 * Created with IntelliJ IDEA.
 * User: Kal Spass
 * Date: 16/07/13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public class KnightPiece extends ChessPieceBase {
    KnightPiece(boolean Color) {super(Color);}
    @java.lang.Override

    public boolean isValidMove(int MoveToX,int MoveToY,int MoveFromX,int MoveFromY) {
     if (((MoveFromX-MoveToX)==1||(MoveFromX-MoveToX)==-1)&&((MoveFromY-MoveToY)==2||(MoveFromY-MoveToY)==-2)){
        return true;
        }
     if (((MoveFromX-MoveToX)==2||(MoveFromX-MoveToX)==-2)&&((MoveFromY-MoveToY)==1||(MoveFromY-MoveToY)==-1)){
            return true;
        }
     else
        return false;
    }

    @Override
    public boolean getColor() {
        return this.isWhite;
    }

    @Override
    public String toString() {
        if (isWhite)
            return  "WN";
        else
            return "BN";
    }
}
