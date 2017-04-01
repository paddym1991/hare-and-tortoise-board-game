/**
 * When you land on a Hare square, draw one Hare card from the deck.
 * Do what it says, then place it at the bottom of the deck.
 * <p>
 * The deck contains 1 "shuffle the deck" card, and 2 cards of each other type, for 15 in total.
 * <p>
 * List of all Hare cards:
 * - If there are more players behind you than in front of you, miss a turn. If not, play again.
 * - If equal, of course play again.
 * <p>
 * - Restore your carrot holding to exactly 65.
 * - If you have more than 65, pay extras to the carrot patch; if fewer, draw extras from the carrot patch.
 * <p>
 * - Free Ride!
 * - Your last turn costs nothing; retrieve the carrots you paid to reach this square.
 * <p>
 * - Draw 10 carrots for each lettuce you still hold.
 * - If you have none left, miss a turn.
 * <p>
 * - Show us your carrots!
 * - Count your carrot cards face up to the table so that everyone will know how many you have left.
 * <p>
 * - Give 10 carrots to each player lying behind you in the race (if any).
 * - If you haven't enough carrots, give them five each; if still not possible, one each. A player who doesn't want extra carrots may discard them to the 'carrot patch'.
 * <p>
 * - Lose half your carrots!
 * - If an odd number, keep the odd one.
 * <p>
 * - Shuffle the hare cards and receive from each player 1 carrot for doing so.
 * - Do not replace this card at the bottom of the pack but include it in the shuffle.
 */
public class HareSquare extends Square {
    private static int[] deck = new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7};
    private static String[] cardText = {"If there are more players behind you than in front of you, miss a turn. If not, play again. If equal, of course play again.", //at index 0
            "Restore your carrot holding to exactly 65. If you have more than 65, pay extras to the carrot patch; if fewer, draw extras from the carrot patch.", //at index 1
            "Free Ride! Your last turn costs nothing; retrieve the carrots you paid to reach this square.", //at index 2
            "Draw 10 carrots for each lettuce you still hold. If you have none left, miss a turn.", //at index 3
            "Show us your carrots! Count your carrot cards face up to the table so that everyone will know how many you have left.", //at index 4
            "Give 10 carrots to each player lying behind you in the race (if any). If you haven't enough carrots, give them five each; if still not possible, one each. A player who doesn't want extra carrots may discard them to the 'carrot patch'.", //at index 5
            "Lose half your carrots! If an odd number, keep the odd one.", //at index 6
            "Shuffle the hare cards and receive from each player 1 carrot for doing so. Do not replace this card at the bottom of the pack but include it in the shuffle.", //at index 7
    };
    private static int currentCard = 0;

    /*
    Once Player lands on HareSquare, Player will be told they have landed on this square.
    Next card in Hare deck will then be chosen and instructions will be carried out.
     */
    @Override
    public void onLandOn(Player player) {
        //player is told what square they have landed on.
        //player picks top card from deck of Hare Cards and completes task on card.
        System.out.println("You have landed on a Hare Square. Please pick up a Hare card");
        //System.out.println( getCardText(currentCard) );
        switch (currentCard) {
            case 0:
                System.out.println("If there are more players behind you than in front of you, miss a turn. If not, play again. If equal, of course play again.");
                if ( player.getRacePosition() > player.getBoard().noOfPlayers() / 2 ) {
                    //skip your next go
                } else {
                    //take a second go
                }
                break;
            case 1:
                System.out.println("Restore your carrot holding to exactly 65. If you have more than 65, pay extras to the carrot patch; if fewer, draw extras from the carrot patch.");
                break;
            case 2:
                System.out.println("Free Ride! Your last turn costs nothing; retrieve the carrots you paid to reach this square.");
                break;
            case 3:
                System.out.println("Draw 10 carrots for each lettuce you still hold. If you have none left, miss a turn.");
                int lettuce = 3;
                if ( lettuce > 0 && lettuce <= 3 ) {
                    System.out.println("Draw 10 carrots for every lettuce card you have");
                } else if ( lettuce == 0 ) {
                    System.out.println("You have no lettuce cards left, miss a turn");
                }
                break;
        case 4:
        System.out.println("Show us your carrots! Count your carrot cards face up to the table so that everyone will know how many you have left.");
        break;
        case 5:
        System.out.println("Give 10 carrots to each player lying behind you in the race (if any). If you haven't enough carrots, give them five each; if still not possible, one each. A player who doesn't want extra carrots may discard them to the 'carrot patch'.");
        break;
        case 6:
        System.out.println("Lose half your carrots! If an odd number, keep the odd one.");
        break;
        case 7:
        System.out.println("Shuffle the hare cards and receive from each player 1 carrot for doing so. Do not replace this card at the bottom of the pack but include it in the shuffle.");
        break;
        default:
        //TODO: display some message to indicate something has gone wrong.
        break;
    }


    currentCard++;
        if(currentCard >deck.length)currentCard =0;
}

    private String getCardText(int positionInDeck) {
        return cardText[deck[positionInDeck]];
    }

    //random shuffle of cards
    //found code for this at: https://www.youtube.com/watch?v=8I37elnmZ2I
    //
    public static void shuffle() {
        int noOfElements = deck.length;

        for (int i = 0; i < noOfElements; i++) {
            int s = i + (int) (Math.random() * (noOfElements - i));
            //swaps two values (at index 's' and 'i')
            int temp = deck[s];
            deck[s] = deck[i];
            deck[i] = temp;
        }
    }

}
