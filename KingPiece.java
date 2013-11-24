package ChessInputDrill;

/**
 * Created with IntelliJ IDEA.
 * User: Kal Spass
 * Date: 16/07/13
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
public class KingPiece extends ChessPieceBase {
    KingPiece(boolean Color)
    { super(Color);}
    boolean HasMoved=false;
    public boolean PieceHasMoved (){
        return this.HasMoved;
    }
    @java.lang.Override
    public boolean isValidMove(int MoveToX,int MoveToY,int MoveFromX,int MoveFromY) {
        if ((((MoveFromX-MoveToX)==1)||((MoveFromX-MoveToX)==0)||((MoveFromX-MoveToX)==-1))&&(((MoveFromY-MoveToY)==1)||((MoveFromY-MoveToY)==0)||((MoveFromY-MoveToY)==-1))){
            return true;
        }
        if (MoveFromX-MoveToX==0&&(MoveFromY-MoveToY==2||MoveFromY-MoveToY==-2))
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
            return  "WK";
        else
            return "BK";
    }
}
