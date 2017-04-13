/**
 * This class handles the Player on the game board.
 * Along with the standard constructors, getters and setters there are methods that :
 * -- Calculate the cost of carrots needed to move to a square chosen by the player
 * --
 *
 *
 *
 *
 * @author
 * @version
 */

public class Player {
    private int boardPosition = 0;
    private Board board;
    private boolean faceDown;
    private int carrots = 65;
    private int lettuce = 3;
    private boolean turnEnded = false;
    private int carrotsSpent = 0; //when player moves, carrotsSpent is assigned costToMove
    private boolean anotherTurn;

    /**
     *
     * @param board
     */
    public Player(Board board) {
        this.board = board;
    }

    /**
     *
     * @return
     */
    public Board getBoard() {
        return board;
    }

    /**
     *
     * @return
     */
    public int getBoardPosition() {
        return boardPosition;
    }

    /**
     *
     * @return
     */
    public String getFormattedBoardPosition() {
        if (atEnd()) return "Finished";
        if (boardPosition == 0) return "Start";
        return boardPosition + "";
    }

    /**
     *
     * @return
     */
    public Square getSquare() {
        return board.getSquare(boardPosition);
    }

    /**
     *
     * @param numSquares
     * @return
     */
    public static int costToMove(int numSquares) {
        return (numSquares * (numSquares + 1))/2;
    }

    /**
     *
     * @param numSquares
     */
    public void movePlayer(int numSquares) {
        carrotsSpent = costToMove(numSquares);
        takeCarrots(carrotsSpent);
        boardPosition += numSquares;
    }

    /**
     *
     * @return
     */
    public boolean atEnd() {

        return board.atEnd(getBoardPosition()); //the board knows the rules for being at the end of the board.
                                                //so ask board are we at the end (.atEnd) by giving it our board position
                                                //(getBoardPosition).
    }

    /**
     *
     */
    public void resetCarrots() {
        carrots = 65;
    }

    /**
     *
     * @return
     */
    public int getCarrots() {
        return carrots;
    }

    /**
     *
     * @param amount
     */
    public void addCarrots(int amount) {
        carrots += amount;
    }

    /**
     *
     * @param amount
     */
    public void takeCarrots(int amount) {
       carrots -= amount;
    }

    /**
     *
     * @return
     */
    public int getCarrotsSpent() {
        return carrotsSpent;
    }

   // public boolean canMove(int numSquares) {return enoughCarrots( numSquares) && getSquareFree(numSquares);}

    /**
     *
     * @param numSquares
     * @return
     */
    public boolean getSquareFree(int numSquares) {
        return board.getSquare(boardPosition + numSquares).canLandOn(this);
    }

    /**
     *
     * @param numSquares
     * @return
     */
    public boolean enoughCarrots(int numSquares){
        return (costToMove(numSquares) <= carrots);
    }

    /**
     *
     * @return
     */
    public int getLettuce() {
        return lettuce;
    }

    /**
     *
     */
    public void takeLettuce() {
        //Reduce the player's stock of lettuce by one. No way to gain extra lettuce, an so addLettuce() method isn't needed.
        lettuce--;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return board.getPlayerName(this);
    }

    /**
     *
     * @return
     */
    public int getRacePosition() {
        return board.getRacePosition(this);

        //Get position in race relative to other players (1st, 2nd, etc.)

        /**
         *
          */
    }

    /**
     *
     */
    public void endTurn() {
        //TODO: Called whenever an effect would end the player's turn without allowing them to move
        turnEnded = true;
    }

    /**
     *
     * @return
     */
    public boolean hasTurnEnded() {
        return turnEnded;
    }

    /**
     * Clears the turn ended flag.
     */
    public void resetTurnStatus() {
        turnEnded = false;
    }

    /**
     *
     * @return
     */
    //Used by Lettuce squares
    public boolean isFaceDown() {
        return faceDown;
    }

    /**
     *
     */
    public void flipFaceDown() {
        faceDown = true;
    } //House rule:    If you have to skip your next go, flip your token face down to keep track of it. On your next go, flip it up and skip your go.

    public void flipFaceUp() {
        faceDown = false;
    }

    public boolean hasAnotherTurn() {
        return anotherTurn;
    }

    public void noMoreTurns() {
        anotherTurn = false;
    }

    public void takeAnotherTurn() {
        anotherTurn = true;
    }

    public void backToTortoise() {
        int newPosition = board.getClosestTortoise(boardPosition);
        addCarrots(10 * (boardPosition - newPosition));
        boardPosition = newPosition;
    }

    /**
     * Checks for each square the player can move to, including Tortoise sqauare. See if all are occupied.
     */
    public boolean noValidMoves() {
        int i, incr;
        for (i = incr = 1; (i+incr < carrots) && (getBoardPosition()+incr < board.length()); i += ++incr) { //i = 1, 3, 6, 10, etc.; incr = 1, 2, 3, 4, etc.
            if (board.getSquare(getBoardPosition() + incr).canLandOn(this)) {
                return false;
            }
        }
        if (board.getClosestTortoise(getBoardPosition()) > -1
                && board.getSquare(board.getClosestTortoise(getBoardPosition())).isOccupied(this)) {
            return false;
        }
        return true;
    }

    public void returnToStart() {
        boardPosition = 0;
    }
}




