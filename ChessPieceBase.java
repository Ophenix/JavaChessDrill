package ChessInputDrill;

/**
 * Created with IntelliJ IDEA.
 * User: Kal Spass
 * Date: 16/07/13
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public abstract class ChessPieceBase {
    protected boolean isWhite;
    ChessPieceBase(boolean isWhite) {
        this.isWhite=isWhite;
    }
    public abstract boolean getColor();
    public  abstract String toString();

    public abstract boolean isValidMove(int MoveToX, int MoveToY, int MoveFromX, int MoveFromY);
}
