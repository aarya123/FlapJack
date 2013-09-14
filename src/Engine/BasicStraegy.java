package Engine;

import java.util.ArrayList;

/* Skeleton code for the Game class*/

public class BasicStrategy {

  public static Move nextMove(Hand playerHand, Card dealerCard) {
    int playerIndex = BasicStrategy.getPlayerIndex(playerCard);
    int dealerIndex = BasicStrategy.getDealerIndex(dealerCard);
    return BasicStrategy.moveInTable(playerIndex, dealerIndex);
  }

  public 

  public static String moveInTable(playerIndex, dealerIndex) {
    Move[][] table =
      { {'H', 'H', 'H', 'H','H', 'H', 'H', 'H', 'H', 'H'}, // 5
        {'H', 'H', 'H', 'H','H', 'H', 'H', 'H', 'H', 'H'}, // 6
        {'H', 'H', 'H', 'H','H', 'H', 'H', 'H', 'H', 'H'}, // 7
        {'H', 'H', 'H', 'H','H', 'H', 'H', 'H', 'H', 'H'}, // 8
        {'H', 'D', 'D', 'D', 'D', 'H', 'H', 'H', 'H', 'H'}, // 9
        {'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'H', 'H'}, // 10
        {'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'H'}, // 11
        {'H', 'D', 'D', 'D', 'D', 'H', 'H', 'H', 'H', 'H'}, // 12


      }
  }


2 3 4 5 6 7 8 9 10  A
5 H H H H H H H H H H
6 H H H H H H H H H H
7 H H H H H H H H H H
8 H H H H H H H H H H
9 H D D D D H H H H H
10  D D D D D D D D H H
11  D D D D D D D D D H
12  H H S S S H H H H H
13  S S S S S H H H H H
14  S S S S S H H H H H
15  S S S S S H H H H H
16  S S S S S H H H H H
17  S S S S S S S S S S
A,2 H H H D D H H H H H
A,3 H H H D D H H H H H
A,4 H H D D D H H H H H
A,5 H H D D D H H H H H
A,6 H D D D D H H H H H
A,7 S DS  DS  DS  DS  S S H H H
A,8 S S S S S S S S S S
A,9 S S S S S S S S S S
2,2 P P P P P P H H H H
3,3 P P P P P P H H H H
4,4 H H H P P H H H H H
5,5 D D D D D D D D H H
6,6 P P P P P H H H H H
7,7 P P P P P P H H H H
8,8 P P P P P P P P P P
9,9 P P P P P S P P S S
T,T S S S S S S S S S S
A,A P P P P P P P P P P
}