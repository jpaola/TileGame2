/**
 * A class that models inserting a tile into the tile game board.
 *
 * @author Paola Jiron 
 */
public class TileGame {

    // instance var's
    private NumberTile[] board;              // the number tile game board
    private int bCount;                      // number of elements on the board

    /**
     * Constructs an empty board.
     */
    public TileGame() {
        //creates the tile board
        this.board = new NumberTile[1];

        this.bCount = 0;                        // initializes the board count 

        this.board[bCount++] = new NumberTile();     // initializes the board

    }

    /**
     * Accessor for the game board.
     *
     * @return The game board.
     */
    public NumberTile[] getBoard() {
        
        return this.board;
    }

    /**
     * Creates and returns a hand of 5 random number tiles.
     *
     * @return A hand (five tiles).
     */
    public NumberTile[] getHand() {

        NumberTile[] hand = new NumberTile[5];

        for (int i = 0; i < 5; i++) {
            
            hand[i] = new NumberTile();
        }

        return hand;        // hand with 5 tiles
    }

    /**
     * If the current tile fits in the board without rotating it, then return
     * index i of the tile in the board so that the current tile fits before ti
     * for i = 0...k-1, or return k if the current tile fits after the last
     * tile; if the tile does not fit, return -1.
     *
     * @param currentTile The current tile.
     * @return The index value where the tile will be placed on the board.
     */
    public int getIndexForFit(NumberTile currentTile) {

        // for each tile on the board...
        
        for (int i = 0; i < bCount; i++) {
            
            // compare the value on the right of the tile with the value on
            // the left side of the tile on the board
            
            if (currentTile.getRight() == board[i].getLeft()) {

                // if the matching tile is the first tile on the board
                // OR if the left side value of the tile matches the right
                // side value of the next tile on the board
                
                if (i == 0 || currentTile.getLeft() == board[i - 1].getRight()) {
                    
                    return i; // place the tile at index i

                } 
                // otherwise, if left hand value on tile matches the right hand
                // value of the last tile on board..
                
                else if (currentTile.getLeft() == board[board.length - 1].getRight()) {

                    return board.length; // place the tile at the end
                }
            }
        }
        return -1; // return -1 if tile does not fit

    }

    /**
     * Calls the method getIndexForFit to see whether a tile can be inserted
     * into the board (In this method the tile can be rotated); If the tile can
     * be inserted return true, else if the tile does not fit after rotating (at
     * most 3 times), return false.
     *
     * @param currentTile The current tile.
     * @return True if the tile can be inserted, false if the tile cannot be
     * inserted after 3 rotations.
     */
    public boolean canInsertTile(NumberTile currentTile) {

        int index = getIndexForFit(currentTile);     // find fit

        if (index >= 0) // if in range..
        {
            NumberTile[] saveBoard = board;          // temp save board

            board = new NumberTile[saveBoard.length + 1];    // expand board

            board[index] = currentTile;          // place the tile on board

            for (int p = 0; p < index; p++) // put all tiles back
            {
                board[p] = saveBoard[p];
            }
            for (int q = index + 1; q < board.length; q++) {
                board[q] = saveBoard[q - 1];
            }
            return true;                                     // set flag
        } else if (index == -1) // if no fit..
        {
            for (int n = 0; n < 3; n++) // rotate the tile to find fit
            {
                currentTile.rotate();

                index = getIndexForFit(currentTile);     // check index

                if (index >= 0) // if in range..
                {
                    NumberTile[] save = board;           // expand board size

                    board = new NumberTile[save.length + 1];

                    board[index] = currentTile;      // place the tile on board

                    for (int r = 0; r < index; r++) // place all others back
                    {
                        board[r] = save[r];
                    }
                    for (int s = index + 1; s < board.length; s++) {
                        board[s] = save[s - 1];
                    }
                    return true;         // set flag
                }
            }
        }

        return false;       // no fit was found
    }

    /**
     * If a tile in the hand fits on board then return true to remove it from
     * the hand and place it correctly on the board, else false to add another
     * tile to the hand.
     *
     * @param hand A player's hand.
     * @return True if a tile fit the board, false otherwise.
     */
    public NumberTile[] makeMove(NumberTile[] hand) {

        for (int i = 0; i < hand.length; i++) // inspect the player's hand
        {
            if (this.canInsertTile(hand[i])) // does any tile fit?
            {
                NumberTile[] saveHand = hand;       // if so, save hand, and expand it

                hand = new NumberTile[hand.length - 1];

                for (int p = 0; p < i; p++) // move tiles a position up
                {
                    hand[p] = saveHand[p];
                }
                for (int q = i + 1; q < saveHand.length; q++) // move others down
                {
                    hand[q - 1] = saveHand[q];
                }
                return hand;                    // return adjusted hand
            }
        }
        NumberTile newTile = new NumberTile();     // when no fit was found..

        NumberTile[] save = hand;
        hand = new NumberTile[save.length + 1];   // add an additional tile to hand

        for (int i = 0; i < save.length; i++) {
            
            hand[i] = save[i];
        }

        hand[hand.length - 1] = newTile;

        return hand;            // return hand + 1 tile
    }

    /**
     * Returns a string of tiles given a list.
     *
     * @param tiles A list of number tiles.
     * @return A String of tiles.
     */
    public String toString(NumberTile[] tiles) {

        // local var's
        
        String outTops = " ";
        String outSides = " ";
        String outBottoms = " ";

        for (int i = 0; i < tiles.length; i++) {

            // concatinates all top values 
            
            outTops += "\t" + "\t" + tiles[i].getTop() + "\t";

        }
        for (int j = 0; j < tiles.length; j++) {

            // concatinates all left and right values 
            
            outSides += "\t" + tiles[j].getLeft() + "\t" + "\t" + tiles[j].getRight();

        }
        for (int k = 0; k < tiles.length; k++) {

            // concatinates all bottom values 
            
            outBottoms += "\t\t" + tiles[k].getBottom() + "\t";

        }

        return outTops + "\n" + outSides + "\n" + outBottoms;
    }
}   // end of TileGame class
