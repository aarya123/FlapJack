package Engine;

import java.util.Arrays;
import java.util.List;

/* Skeleton code for the Game class*/

public class BasicStrategy {

    public static String nextMove(Hand playerHand, Card dealerCard) {
        int playerIndex = BasicStrategy.getPlayerIndex(playerHand);
        int dealerIndex = BasicStrategy.getDealerIndex(dealerCard);
        return BasicStrategy.moveInTable(playerIndex, dealerIndex);
    }

    public static int getPlayerIndex(Hand playerHand) {
        // A, A
        List<Card> cards = playerHand.getCards();
        if (cards.size() == 2 && cards.get(0).getRank().equals("A") && cards.get(1).getRank().equals("A")) {
            return 30;

            // T, T
        } else if (Arrays.equals(playerHand.getValues(), new int[]{20})) {
            return 29;


            // A + something
        } else if (Arrays.equals(playerHand.getValues(), new int[]{3, 13})) { // A, 2
            return 13;
        } else if (Arrays.equals(playerHand.getValues(), new int[]{4, 14})) { // A, 3
            return 14;
        } else if (Arrays.equals(playerHand.getValues(), new int[]{5, 15})) { // A, 4
            return 15;
        } else if (Arrays.equals(playerHand.getValues(), new int[]{6, 16})) { // A, 5
            return 16;
        } else if (Arrays.equals(playerHand.getValues(), new int[]{7, 17})) { // A, 6
            return 17;
        } else if (Arrays.equals(playerHand.getValues(), new int[]{8, 18})) { // A, 7
            return 18;
        } else if (Arrays.equals(playerHand.getValues(), new int[]{9, 19})) { // A, 8
            return 19;
        } else if (Arrays.equals(playerHand.getValues(), new int[]{10, 20})) { // A, 9
            return 20;

            // Doubles
        } else if (cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank()) {
            if (Arrays.equals(playerHand.getValues(), new int[]{4}))
                return 21;
            else if (Arrays.equals(playerHand.getValues(), new int[]{6}))
                return 22;
            else if (Arrays.equals(playerHand.getValues(), new int[]{8}))
                return 23;
            else if (Arrays.equals(playerHand.getValues(), new int[]{10}))
                return 24;
            else if (Arrays.equals(playerHand.getValues(), new int[]{12}))
                return 25;
            else if (Arrays.equals(playerHand.getValues(), new int[]{14}))
                return 26;
            else if (Arrays.equals(playerHand.getValues(), new int[]{16}))
                return 27;
            else if (Arrays.equals(playerHand.getValues(), new int[]{18}))
                return 28;
            else
                return 0;
        } else {
            return Math.abs(Math.min(playerHand.getValues()[0], 17) - 5);
        }
    }

    public static int getDealerIndex(Card dealerCard) {
        if (dealerCard.getRank() == "A")
            return 9;
        else
            return dealerCard.getValues()[0] - 2;
    }

    public static String moveInTable(int playerIndex, int dealerIndex) {
        String[][] table =
                {{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 5
                        {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 6
                        {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 7
                        {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 8
                        {"H", "D", "D", "D", "D", "H", "H", "H", "H", "H"}, // 9
                        {"D", "D", "D", "D", "D", "D", "D", "D", "H", "H"}, // 10
                        {"D", "D", "D", "D", "D", "D", "D", "D", "D", "H"}, // 11
                        {"H", "D", "D", "D", "D", "H", "H", "H", "H", "H"}, // 12
                        {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 13
                        {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 14
                        {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 15
                        {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 16
                        {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // 17
                        {"H", "H", "H", "D", "D", "H", "H", "H", "H", "H"}, // A,2
                        {"H", "H", "H", "D", "D", "H", "H", "H", "H", "H"}, // A,3
                        {"H", "H", "D", "D", "D", "H", "H", "H", "H", "H"}, // A,4
                        {"H", "H", "D", "D", "D", "H", "H", "H", "H", "H"}, // A,5
                        {"H", "D", "D", "D", "D", "H", "H", "H", "H", "H"}, // A,6
                        {"S", "D", "D", "D", "D", "S", "S", "H", "H", "H"}, // A,7
                        {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // A,8
                        {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // A,9
                        {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 2,2
                        {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 3,3
                        {"H", "H", "H", "P", "P", "H", "H", "H", "H", "H"}, // 4,4
                        {"D", "D", "D", "D", "D", "D", "D", "D", "H", "H"}, // 5,5
                        {"P", "P", "P", "P", "P", "H", "H", "H", "H", "H"}, // 6,6
                        {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 7,7
                        {"P", "P", "P", "P", "P", "P", "P", "P", "P", "P"}, // 8,8
                        {"P", "P", "P", "P", "P", "S", "P", "P", "S", "S"}, // 9,9
                        {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // T,T
                        {"P", "P", "P", "P", "P", "P", "P", "P", "P", "P"} // A,A
                };

        return table[playerIndex][dealerIndex];
    }


//2 3 4 5 6 7 8 9 10  A
//5 H H H H H H H H H H
//6 H H H H H H H H H H
//7 H H H H H H H H H H
//8 H H H H H H H H H H
//9 H D D D D H H H H H
//10  D D D D D D D D H H
//11  D D D D D D D D D H
//12  H H S S S H H H H H
//13  S S S S S H H H H H
//14  S S S S S H H H H H
//15  S S S S S H H H H H
//16  S S S S S H H H H H
//17  S S S S S S S S S S
//A,2 H H H D D H H H H H
//A,3 H H H D D H H H H H
//A,4 H H D D D H H H H H
//A,5 H H D D D H H H H H
//A,6 H D D D D H H H H H
//A,7 S D  D  D  D  S S H H H
//A,8 S S S S S S S S S S
//A,9 S S S S S S S S S S
//2,2 P P P P P P H H H H
//3,3 P P P P P P H H H H
//4,4 H H H P P H H H H H
//5,5 D D D D D D D D H H
//6,6 P P P P P H H H H H
//7,7 P P P P P P H H H H
//8,8 P P P P P P P P P P
//9,9 P P P P P S P P S S
//T,T S S S S S S S S S S
//A,A P P P P P P P P P P
}