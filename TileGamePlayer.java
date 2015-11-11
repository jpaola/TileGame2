
import java.util.Scanner;

/**
 * Plays a number tile game. A number tile in the player's hand can be inserted
 * but once on the board its position is fixed. If a move cannot be made then
 * one tile is added to the player's hand and the other player gets to move. A
 * turn is when player1 makes a move and then player2 makes a move (to make the
 * game fair). The game ends when all the tiles are removed from one (or both)
 * player's hand(s). If one player's hand is empty and the other player's hand
 * still contains tiles then the player with the empty hand is the winner. If
 * both player's hands are empty then the game ends in a tie.
 */
/**
 *
 * @author Paola Jiron 
 */
public class TileGamePlayer {

    public static void main(String[] args) {
        
        // scans user response after each game
        
        Scanner in = new Scanner(System.in);
        String input;

        do {
            
            // instance of a board
            
            TileGame game = new TileGame();
           
            // prepare hands
           
            NumberTile[] player1 = game.getHand();
            NumberTile[] player2 = game.getHand();
            
    
           // save initial player hands
            
            String p1InitialHand = "Player 1's Initial Hand:\n"
                    + game.toString(player1) + "\n";

            String p2InitialHand = "Player 2's Initial Hand:\n"
                    + game.toString(player2) + "\n";
             
            
           // make moves until one or both players hand is empty
            
            while ((player1.length != 0) && (player2.length != 0)) {

                
                 player1 = game.makeMove(player1);
                 player2 = game.makeMove(player2);
                 
           }
            
           // get the game board
            
           NumberTile[] board = game.getBoard();
                        
                        
           // if player 1's hand is empty, player 1 wins and player 2 loses
            
            if ((player1.length == 0  ) && (player2.length != 0)) {
                System.out.println("Player 1 WINS!");

               // display initial hands of both players
                
                System.out.println(p1InitialHand + "\n" + p2InitialHand);

                // display the final hands and board
                
                System.out.println("Player 1's Final Hand:\n"
                        + game.toString(player1) + "\n\nPlayer 2's Final Hand:\n"
                        + game.toString(player2) + "\n\nFinal Board:\n"
                        + game.toString(board));
            }
           // if player 2's hand is empty, player 2 wins and player 1 loses

            if ((player1.length != 0) && (player2.length == 0)) {
                System.out.println("Player 2 WINS!");

               // display initial hands of both players
                
                System.out.println(p1InitialHand + "\n" + p2InitialHand);

               // display final hands and board
                
                System.out.println("Player 1's Final Hand:\n"
                        + game.toString(player1) + "\n\nPlayer 2's Final Hand:\n"
                        + game.toString(player2) + "\n\nFinal Board:\n"
                        + game.toString(board));
                
            } // players are tied
            
            else if ((player1.length == 0) && (player2.length == 0)) {
                System.out.println("There is a TIE!");

               // display initial hands of both players
                
                System.out.println(p1InitialHand + "\n" + p2InitialHand);

               // display final hands and board
                
                System.out.println("Player 1's Final Hand:\n"
                        + game.toString(player1) + "\n\nPlayer 2's Final Hand:\n"
                        + game.toString(player2) + "\n\nFinal Board:\n"
                        + game.toString(board) + "\n");
            }
 
            
           // ask user if he/she would like to play again
            
            System.out.println("Want to play again? (Type YES to play"
                    + ", NO to quit)\n");

            input = in.next();   // user's response

        } while (input.equalsIgnoreCase("yes"));
    }
} // end of TileGamePlayer class