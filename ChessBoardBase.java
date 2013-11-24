package ChessInputDrill;

import java.io.*;
import java.lang.*;

public class ChessBoardBase {
    static int MoveToX, MoveToY, MoveFromX, MoveFromY;
    static String InputMove="";
    public static void main(String[] args) {

        boolean WhiteKingThreat= false, BlackKingThreat= false, WhiteMove=true, GameIsWon=false, EnPassantPossible=false, WhiteCastlingPossible=true, BlackCastlingPossible=true, CorrectMoveInputed=true;

        System.out.println("Hello. Welcome to myChess! Here is your damned board.");
        BishopPiece BWhite= new BishopPiece(true);
        BishopPiece BBlack= new BishopPiece(false);
        RookPiece RWhite = new RookPiece(true);
        RookPiece RBlack = new RookPiece(false);
        KingPiece KWhite = new KingPiece(true);
        KingPiece KBlack = new KingPiece(false);
        QueenPiece QWhite = new QueenPiece(true);
        QueenPiece QBlack = new QueenPiece(false);
        PawnPiece PBlack = new PawnPiece(false);
        PawnPiece PWhite = new PawnPiece(true);
        KnightPiece NBlack = new KnightPiece(false);
        KnightPiece NWhite = new KnightPiece(true);
        EmptySpace Blank = new EmptySpace(true);
        ChessPieceBase[][] myChessBoard = {
                {RBlack, NBlack,BBlack,KBlack, QBlack, BBlack, NBlack,RBlack},
                {PBlack,PBlack,PBlack,PBlack,PBlack,PBlack,PBlack,PBlack },
                {Blank,Blank,Blank,Blank,Blank,Blank,Blank,Blank},
                {Blank,Blank,Blank,Blank,Blank,Blank,Blank,Blank},
                {Blank,Blank,Blank,Blank,Blank,Blank,Blank,Blank},
                {Blank,Blank,Blank,Blank,Blank,Blank,Blank,Blank},
                {PWhite,PWhite,PWhite,PWhite,PWhite,PWhite,PWhite,PWhite},
                {RWhite, NWhite,BWhite,KWhite, QWhite, BWhite, NWhite,RWhite}};

        //todo: EnPassant. Add rule about moveing Pawns 2 steps. Castling.
        while (GameIsWon==false) {
            PrintChessBoard(myChessBoard);
            System.out.println(WhiteMove?"White player, please choose a unit to move and press enter.":"Black player, please choose a unit to move and press enter.");
            System.out.println("Please us the standard format of A1 to H8. Which unit would you like to move?");
            do {
                ReadPieceMoveFrom(InputMove=input());
                if (MoveFromX==9||MoveFromY==9)
                    System.out.println("Incorrect input. Please choose a move using the standard A1 to H8 system. Case sensitive input :)");
                else
                    CorrectMoveInputed = false;
            }  while (CorrectMoveInputed);
            do {
                System.out.println("Please choose a space to move the unit to and press enter.");
                ReadPieceMoveTo(InputMove = input());
                if (MoveToX==9||MoveToY==9)
                    System.out.println("Incorrect input. Please choose a move using the standard A1 to H8 system. Case sensitive input :)");
                else
                    CorrectMoveInputed=false;
            } while (CorrectMoveInputed);
            if (myChessBoard[MoveFromX][MoveFromY].isWhite==WhiteMove){
                if (isInBoardLimits(MoveToX,MoveToY,MoveFromX,MoveFromY)){ //consider
                    if (myChessBoard[MoveFromX][MoveFromY].isValidMove(MoveToX,MoveToY,MoveFromX,MoveFromY)){
                        // add king threat check to EnPassant before the continue.
                        if (EnPassantPossible=true) {
                            if (myChessBoard[MoveFromX][MoveFromY]instanceof PawnPiece&& (MoveFromX==6||MoveFromX==3)&&(MoveFromY-MoveToY==-1||MoveFromY-MoveToY==1)){
                                myChessBoard[MoveToX][MoveToY]=myChessBoard[MoveFromX][MoveFromY];
                                myChessBoard[MoveFromX][MoveToY]=Blank;
                                System.out.println("You have performed an EnPassant.");

                                continue;
                            }
                        }
                        EnPassantPossible=false;
                    }
                    if (myChessBoard[MoveFromX][MoveFromY]instanceof PawnPiece&& (MoveFromX==4||MoveFromX==1)&&(MoveToX==6||MoveToX==3)){
                        EnPassantPossible=true;
                    }


                    if (myChessBoard[MoveFromX][MoveFromY]instanceof KingPiece&&MoveFromX-MoveToX==0&&(MoveFromY-MoveToY==2||MoveFromY-MoveToY==-2))
                    {
                        if (((KingPiece)myChessBoard[MoveFromX][MoveFromY]).PieceHasMoved()==false){
                            if (MoveFromY-MoveToY==2 && WhiteMove&&(!((RookPiece)myChessBoard[7][0]).PieceHasMoved())&&isUnblockedMove(MoveToX,MoveToY,MoveFromX,MoveFromY,myChessBoard)&&isUnblockedMove(7,3, 7,0, myChessBoard)){
                                if ((!IsSquareThreatened(MoveFromX, MoveFromY, WhiteMove,myChessBoard))&&(!IsSquareThreatened(MoveFromX, MoveFromY-1, WhiteMove,myChessBoard))&&(!IsSquareThreatened(MoveToX, MoveToY, WhiteMove,myChessBoard))) {
                                    myChessBoard[MoveToX][MoveToY]=myChessBoard[MoveFromX][MoveFromY];
                                    myChessBoard[MoveFromX][MoveFromY]=Blank;
                                    myChessBoard[7][3]=myChessBoard[7][0];
                                    myChessBoard[7][0]=Blank;
                                    continue;
                                }

                            }
                            if (MoveFromY-MoveToY==-2 && WhiteMove&&(!((RookPiece)myChessBoard[7][7]).PieceHasMoved())&&isUnblockedMove(MoveToX,MoveToY,MoveFromX,MoveFromY,myChessBoard)&&isUnblockedMove(7,5, 7,7, myChessBoard)){
                                myChessBoard[MoveToX][MoveToY]=myChessBoard[MoveFromX][MoveFromY];
                                myChessBoard[MoveFromX][MoveFromY]=Blank;
                                myChessBoard[7][5]=myChessBoard[7][7];
                                myChessBoard[7][7]=Blank;
                                continue;

                            }
                            if (MoveFromY-MoveToY==2 && (!WhiteMove)&&(!((RookPiece)myChessBoard[0][0]).PieceHasMoved())&&isUnblockedMove(MoveToX,MoveToY,MoveFromX,MoveFromY,myChessBoard)&&isUnblockedMove(0,3, 0,0, myChessBoard)){
                                myChessBoard[MoveToX][MoveToY]=myChessBoard[MoveFromX][MoveFromY];
                                myChessBoard[MoveFromX][MoveFromY]=Blank;
                                myChessBoard[0][3]=myChessBoard[0][0];
                                myChessBoard[0][0]=Blank;
                                continue;

                            }
                            if (MoveFromY-MoveToY==-2 && (!WhiteMove)&&(!((RookPiece)myChessBoard[0][7]).PieceHasMoved())&&isUnblockedMove(MoveToX,MoveToY,MoveFromX,MoveFromY,myChessBoard)&&isUnblockedMove(7,5, 0,7, myChessBoard)){
                                myChessBoard[MoveToX][MoveToY]=myChessBoard[MoveFromX][MoveFromY];
                                myChessBoard[MoveFromX][MoveFromY]=Blank;
                                myChessBoard[0][5]=myChessBoard[0][7];
                                myChessBoard[0][7]=Blank;
                                continue;

                            }

                        }
                    }

                    if (isUnblockedMove(MoveToX,MoveToY,MoveFromX,MoveFromY,myChessBoard)){
                        // add check king threat before.
                        ChessPieceBase[][] MoveTestBoard = new ChessPieceBase[8][8];
                        for (int a=0;a<8;a++){
                            for (int b=0;b<8;b++){
                                MoveTestBoard[a][b]= myChessBoard[a][b];
                            }
                        }
                            MovePieces(MoveToX,MoveToY,MoveFromX,MoveFromY,MoveTestBoard,Blank);
                            if (!CheckKingThreat(MoveTestBoard,WhiteMove)){
                                System.out.println("Your king is threatened. This move is illegal.");
                                continue;
                            }
                        // add stalemante rules.
                        // add checkmate rules.
                    }
                        // add stalemante rules.
                        // add checkmate rules.
                        if (MovePieces(MoveToX,MoveToY,MoveFromX,MoveFromY,myChessBoard,Blank)){
                            WhiteMove=!WhiteMove;
                            System.out.println("Move successful.");
                            PrintChessBoard(myChessBoard);
                        }
                    }
                }
                else System.out.println("This is not a legal piece to move. Choose a "+ (WhiteMove?"white":"black") +"Piece to move");
        }
    }
    public static boolean MovePieces (int MoveToX, int MoveToY, int MoveFromX, int MoveFromY, ChessPieceBase[][] TheBoard,EmptySpace Blank){
        if (TheBoard[MoveToX][MoveToY] instanceof EmptySpace || TheBoard[MoveToX][MoveToY].isWhite!=TheBoard[MoveFromX][MoveFromY].isWhite)
        {
            TheBoard[MoveToX][MoveToY]=TheBoard[MoveFromX][MoveFromY];
            TheBoard[MoveFromX][MoveFromY]=Blank;
            return true;
        }
        else return false;
    }
    public static void PrintChessBoard (ChessPieceBase[][] TheBoard) {
        System.out.println("   A  B  C  D  E  F  G  H");
        for (int a=0;a<8;a++ )
        {

            String TempLine=(8-a)+"|";

            for (int b=0;b<8;b++)                      {
                TempLine+=TheBoard[a][b];
                TempLine+=" ";
            }
            System.out.println(TempLine);
        }
        System.out.println("Chess board printed."); }
    public static void ReadPieceMoveTo (String InputMove) {

        switch (InputMove.charAt(0)){case 'A': MoveToY=0; break;case 'B': MoveToY=1; break;case 'C': MoveToY=2; break;
            case 'D': MoveToY=3; break;case 'E': MoveToY=4; break;case 'F': MoveToY=5; break;case 'G': MoveToY=6; break;
            case 'H': MoveToY=7; break;default: MoveToY = 9;
        }
        switch (InputMove.charAt(1))
        {case '1': MoveToX=7; break;case '2': MoveToX=6; break;case '3': MoveToX=5; break;
            case '4': MoveToX=4; break;case '5': MoveToX=3; break;case '6': MoveToX=2; break;case '7': MoveToX=1; break;
            case '8': MoveToX=0; break;default: MoveToX = 9;
        }
    }
    public static void ReadPieceMoveFrom (String InputMove) {

        switch (InputMove.charAt(0)){case 'A': MoveFromY=0; break;case 'B': MoveFromY=1; break;case 'C': MoveFromY=2; break;
            case 'D': MoveFromY=3; break;case 'E': MoveFromY=4; break;case 'F': MoveFromY=5; break;case 'G': MoveFromY=6; break;
            case 'H': MoveFromY=7; break;  default: MoveFromY = 9;
        }
        switch (InputMove.charAt(1))
        {case '1': MoveFromX=7; break;case '2': MoveFromX=6; break;case '3': MoveFromX=5; break;
            case '4': MoveFromX=4; break;case '5': MoveFromX=3; break;case '6': MoveFromX=2; break;case '7': MoveFromX=1; break;
            case '8': MoveFromX=0; break; default:MoveFromX=9;
        }
    }
    public static boolean isInBoardLimits (int MoveToX, int MoveToY, int MoveFromX, int MoveFromY){
        if  (MoveToX<8&&MoveToX>-1&&MoveToY<8&&MoveToY>-1&&MoveFromX<8&&MoveFromX>-1&&MoveFromY<8&&MoveFromY>-1)
            return true;
        else
            return false;
    }
    public static boolean isUnblockedMove(int MoveToX, int MoveToY, int MoveFromX, int MoveFromY, ChessPieceBase[][] TheBoard){
        if (TheBoard[MoveFromX][MoveFromY] instanceof KnightPiece){
            return true;
        }
        int MoveXTest=MoveFromX, MoveYTest=MoveFromY;
        if ((MoveFromX-MoveToX)<0&&(MoveFromY-MoveToY)<0){
            MoveXTest++;
            MoveYTest++;
            while (MoveXTest!=MoveToX&&MoveYTest!=MoveToX){
                if (!(TheBoard[MoveXTest][MoveYTest]instanceof EmptySpace)){
                    return false;
                }
                MoveXTest++;
                MoveYTest++;

            }
            return true;
        }
        if ((MoveFromX-MoveToX)>0&&(MoveFromY-MoveToY)<0){
            MoveXTest--;
            MoveYTest++;
            while (MoveXTest!=MoveToX&&MoveYTest!=MoveToX){
                if (!(TheBoard[MoveXTest][MoveYTest]instanceof EmptySpace)){
                    return false;
                }
                MoveXTest--;
                MoveYTest++;

            }
            return true;
        }
        if ((MoveFromX-MoveToX)<0&&(MoveFromY-MoveToY)>0){
            MoveXTest++;
            MoveYTest--;
            while (MoveXTest!=MoveToX&&MoveYTest!=MoveToX){
                if (!(TheBoard[MoveXTest][MoveYTest]instanceof EmptySpace)){
                    return false;
                }
                MoveXTest++;
                MoveYTest--;
            }
            return true;
        }
        if ((MoveFromX-MoveToX)>0&&(MoveFromY-MoveToY)>0){
            MoveXTest--;
            MoveYTest--;
            while (MoveXTest!=MoveToX&&MoveYTest!=MoveToX){
                if (!(TheBoard[MoveXTest][MoveYTest]instanceof EmptySpace)){
                    return false;
                }
                MoveXTest--;
                MoveYTest--;

            }
            return true;
        }
        if ((MoveFromX-MoveToX)==0&&(MoveFromY-MoveToY)<0){
            MoveYTest++;
            while ((MoveYTest-MoveToY)!=0){
                if (!(TheBoard[MoveXTest][MoveYTest]instanceof EmptySpace)){
                    return false;
                }
                MoveYTest++;

            }
            return true;
        }
        if ((MoveFromX-MoveToX)==0&&(MoveFromY-MoveToY)>0){
            MoveYTest--;
            while ((MoveYTest-MoveToY)!=0){
                if (!(TheBoard[MoveXTest][MoveYTest]instanceof EmptySpace)){
                    return false;
                }
                MoveYTest--;

            }
            return true;
        }
        if ((MoveFromX-MoveToX)>0&&(MoveFromY-MoveToY)==0){
            MoveXTest--;
            while ((MoveXTest-MoveToX)!=0){
                if (!(TheBoard[MoveXTest][MoveYTest]instanceof EmptySpace)){
                    return false;
                }
                MoveXTest--;

            }
            return true;
        }
        if ((MoveFromX-MoveToX)<0&&(MoveFromY-MoveToY)==0){
            MoveXTest++;
            while ((MoveXTest-MoveToX)!=0){
                if (!(TheBoard[MoveXTest][MoveYTest]instanceof EmptySpace)){
                    return false;
                }
                MoveXTest++;

            }
            return true;
        }
        return false;
    }
    public static boolean IsSquareThreatened (int SquareX, int SquareY, boolean WhiteMove, ChessPieceBase[][] TheBoard){
        for (int a=0;a<8;a++){
            for (int b=0;b<8;b++){
                if (TheBoard[a][b].isWhite==(!WhiteMove)&&TheBoard[a][b].isValidMove(SquareX,SquareY,a,b)&& isUnblockedMove(SquareX,SquareY,a,b, TheBoard)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean CheckKingThreat(ChessPieceBase[][] TheBoard, boolean WhiteMove){
        int KingLocationX=0,KingLocationY=0;
        for (int a=0;a<8;a++){
            for (int b=0;b<8;b++){
                if (TheBoard[a][b]instanceof KingPiece&& TheBoard[a][b].isWhite==WhiteMove){
                    KingLocationX=a;
                    KingLocationY=b;
                }
            }
        }
        for (int a=0;a<8;a++)
            for (int b = 0; b < 8; b++) {
                if (TheBoard[a][b].isWhite != WhiteMove) {
                    if (TheBoard[a][b].isValidMove(KingLocationX, KingLocationY, a, b)) {
                        return true;
                    }
                }
            }
        return false;
    }
    public static String FindKing (boolean WhiteMove, ChessPieceBase[][] TheBoard){
        int KingLocationX=0,KingLocationY=0;
        for (int a=0;a<8;a++){
            for (int b=0;b<8;b++){
                if (TheBoard[a][b]instanceof KingPiece&& TheBoard[a][b].isWhite==WhiteMove){
                    KingLocationX=a;
                    KingLocationY=b;
                }
            }
        }
        return KingLocationX+""+KingLocationY;

    }
    public boolean CheckForCheckMate(ChessPieceBase[][] TheBoard, boolean WhiteMove, EmptySpace emptySpace) {
        int KingLocationX= FindKing(WhiteMove, TheBoard).charAt(0), KingLocationY= FindKing(WhiteMove, TheBoard).charAt(1);
        for (int a=0;a<8;a++){
            for (int b = 0; b < 8; b++) {
                if (TheBoard[a][b].isWhite != WhiteMove|| TheBoard[a][b]instanceof EmptySpace) {
                    if (TheBoard[a][b].isValidMove(KingLocationX, KingLocationY, a, b)) {
                        if ((TheBoard[KingLocationX-1][KingLocationY-1]instanceof EmptySpace))
                            if (KingLocationX-1>=0&&KingLocationY-1>=0)
                                if (!(IsSquareThreatened(KingLocationX-1, KingLocationY-1,WhiteMove, TheBoard)))
                                    return false;
                        if (KingLocationY-1>=0)
                            if((TheBoard[KingLocationX][KingLocationY-1]instanceof EmptySpace))
                                if (!(IsSquareThreatened(KingLocationX, KingLocationY-1,WhiteMove, TheBoard)))
                                    return false;
                        if (KingLocationX+1<8&&KingLocationY-1>=0)
                            if((TheBoard[KingLocationX+1][KingLocationY-1]instanceof EmptySpace))
                                if (!(IsSquareThreatened(KingLocationX+1, KingLocationY-1,WhiteMove, TheBoard)))
                                    return false;
                        if (KingLocationX+1<8)
                            if((TheBoard[KingLocationX+1][KingLocationY]instanceof EmptySpace))
                                if (!(IsSquareThreatened(KingLocationX+1, KingLocationY,WhiteMove, TheBoard)))
                                    return false;
                        if (KingLocationX+1<8&&KingLocationY+1<8)
                            if((TheBoard[KingLocationX+1][KingLocationY+1]instanceof EmptySpace))
                                if (!(IsSquareThreatened(KingLocationX+1, KingLocationY+1,WhiteMove, TheBoard)))
                                    return false;
                        if (KingLocationY+1<8)
                            if((TheBoard[KingLocationX][KingLocationY+1]instanceof EmptySpace))
                                if (!(IsSquareThreatened(KingLocationX, KingLocationY+1,WhiteMove, TheBoard)))
                                    return false;
                        if (KingLocationX-1>=0&&KingLocationY+1<8)
                            if((TheBoard[KingLocationX-1][KingLocationY+1]instanceof EmptySpace))
                                if (!(IsSquareThreatened(KingLocationX-1, KingLocationY+1,WhiteMove, TheBoard)))
                                    return false;
                        if (KingLocationX-1>=0)
                            if((TheBoard[KingLocationX-1][KingLocationY]instanceof EmptySpace))
                                if (!(IsSquareThreatened(KingLocationX-1, KingLocationY,WhiteMove, TheBoard)))
                                    return false;


                    }
                }
            }
        }
        ChessPieceBase[][] MoveTestBoard = new ChessPieceBase[8][8];
        MoveTestBoard = ClearTestBoard(TheBoard, MoveTestBoard);
        for (int a=0;a<8;a++){
            for (int b = 0; b < 8; b++){
                if (MoveTestBoard[a][b].isWhite==WhiteMove && !(MoveTestBoard[a][b]instanceof KingPiece)) {
                    for (int c=0;c<8;c++ ) {
                         for (int d=0; d<8; d++){
                                 if (MoveTestBoard[a][b].isValidMove(c,d,a,b))
                                     if (MoveTestBoard[c][d]instanceof EmptySpace || MoveTestBoard[c][d].isWhite != WhiteMove){
                                         MovePieces(c,d,a,b, MoveTestBoard,emptySpace);
                                         if (!(CheckKingThreat(MoveTestBoard, WhiteMove)))
                                             return false;
                                         else ClearTestBoard(TheBoard,MoveTestBoard);
                         }
                    }
                }
            }
        }
    }
        return true;
    }
    public static String input(){
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public static ChessPieceBase[][] ClearTestBoard (ChessPieceBase[][]OriginalBoard,ChessPieceBase[][]AlteredBoard ){
        for (int a=0;a<8;a++){
            for (int b=0;b<8;b++){
                AlteredBoard[a][b]= OriginalBoard[a][b];
            }
        }
        return AlteredBoard;
    }
    public static boolean CheckforStalemate(ChessPieceBase[][] TheBoard, boolean WhiteMove, EmptySpace emptySpace){
        if (!(CheckKingThreat(TheBoard,WhiteMove))){
            int KingLocationX= FindKing(WhiteMove, TheBoard).charAt(0), KingLocationY= FindKing(WhiteMove, TheBoard).charAt(1);
            for (int a=0;a<8;a++){
                for (int b = 0; b < 8; b++) {
                    if (TheBoard[a][b].isWhite != WhiteMove|| TheBoard[a][b]instanceof EmptySpace) {
                        if (TheBoard[a][b].isValidMove(KingLocationX, KingLocationY, a, b)) {
                            if ((TheBoard[KingLocationX-1][KingLocationY-1]instanceof EmptySpace))
                                if (KingLocationX-1>=0&&KingLocationY-1>=0)
                                    if (!(IsSquareThreatened(KingLocationX-1, KingLocationY-1,WhiteMove, TheBoard)))
                                        return false;
                            if (KingLocationY-1>=0)
                                if((TheBoard[KingLocationX][KingLocationY-1]instanceof EmptySpace))
                                    if (!(IsSquareThreatened(KingLocationX, KingLocationY-1,WhiteMove, TheBoard)))
                                        return false;
                            if (KingLocationX+1<8&&KingLocationY-1>=0)
                                if((TheBoard[KingLocationX+1][KingLocationY-1]instanceof EmptySpace))
                                    if (!(IsSquareThreatened(KingLocationX+1, KingLocationY-1,WhiteMove, TheBoard)))
                                        return false;
                            if (KingLocationX+1<8)
                                if((TheBoard[KingLocationX+1][KingLocationY]instanceof EmptySpace))
                                    if (!(IsSquareThreatened(KingLocationX+1, KingLocationY,WhiteMove, TheBoard)))
                                        return false;
                            if (KingLocationX+1<8&&KingLocationY+1<8)
                                if((TheBoard[KingLocationX+1][KingLocationY+1]instanceof EmptySpace))
                                    if (!(IsSquareThreatened(KingLocationX+1, KingLocationY+1,WhiteMove, TheBoard)))
                                        return false;
                            if (KingLocationY+1<8)
                                if((TheBoard[KingLocationX][KingLocationY+1]instanceof EmptySpace))
                                    if (!(IsSquareThreatened(KingLocationX, KingLocationY+1,WhiteMove, TheBoard)))
                                        return false;
                            if (KingLocationX-1>=0&&KingLocationY+1<8)
                                if((TheBoard[KingLocationX-1][KingLocationY+1]instanceof EmptySpace))
                                    if (!(IsSquareThreatened(KingLocationX-1, KingLocationY+1,WhiteMove, TheBoard)))
                                        return false;
                            if (KingLocationX-1>=0)
                                if((TheBoard[KingLocationX-1][KingLocationY]instanceof EmptySpace))
                                    if (!(IsSquareThreatened(KingLocationX-1, KingLocationY,WhiteMove, TheBoard)))
                                        return false;
                        }
                    }
                }
            }
            ChessPieceBase[][] MoveTestBoard = new ChessPieceBase[8][8];
            MoveTestBoard = ClearTestBoard(TheBoard, MoveTestBoard);
            for (int a=0;a<8;a++){
                for (int b = 0; b < 8; b++) {
                    for (int c=0; c<8; c++){
                        for (int d=0; d<8; d++){
                            if (MoveTestBoard[c][d]instanceof EmptySpace || MoveTestBoard[c][d].isWhite != WhiteMove){
                                MovePieces(c,d,a,b, MoveTestBoard,emptySpace);
                                if (!(CheckKingThreat(MoveTestBoard, WhiteMove)))
                                    return false;
                                else ClearTestBoard(TheBoard,MoveTestBoard);
                            }
                        }
                    }
                }
            }
            return true;
        }
        return true;
    }

}


