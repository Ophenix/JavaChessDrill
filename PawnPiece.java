package ChessInputDrill;

/**
 * Created with IntelliJ IDEA.
 * User: Kal Spass
 * Date: 16/07/13
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public class PawnPiece extends ChessPieceBase {
    PawnPiece(boolean Color) {super(Color);}

    @java.lang.Override //Panws can move twice once per game. Only move forwards, can eat diagonal.
    public boolean isValidMove(int MoveToX,int MoveToY,int MoveFromX,int MoveFromY) {
        if (isWhite){
            if ((MoveFromX-MoveToX)==1&&(MoveFromY-MoveToY)==0){
                return true;
            }
            if ((MoveFromX-MoveToX)==2&&(MoveFromY-MoveToY)==0&&(MoveFromX==6||MoveFromX==1)){
                return true;
            }
            if ((MoveFromX-MoveToX)==1&&((MoveFromY-MoveToY)==1||(MoveFromY-MoveToY)==-1)){
                return true;
            }
        else
        return false;
        }
            else{
            if ((MoveFromX-MoveToX)==-1&&(MoveFromY-MoveToY)==0){
                return true;
            }
            if ((MoveFromX-MoveToX)==-2&&(MoveFromY-MoveToY)==0){
                return true;
            }
            if ((MoveFromX-MoveToX)==-1&&((MoveFromY-MoveToY)==1||(MoveFromY-MoveToY)==-1)){
                return true;
            }
            else
                return false;
        }
    }
    public boolean getColor() {
        return this.isWhite;
    }
    @Override
    public String toString() {
        if (isWhite)
            return  "WP";
        else
            return "BP";
    }
}
