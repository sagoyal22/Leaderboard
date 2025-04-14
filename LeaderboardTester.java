//////////////// FILE HEADER ////////////////////////////////////////////////
//
// Title:LeaderboardTester
// Course: CS 300 Fall 2024
//
// Author: saniya goyal
// Email: sagoyal@wisc.edu
// Lecturer: (Blerina Gkotse)
//
///////////////////////////////////////////////////////////////////////////////

public class LeaderboardTester {

  /////////////////////////////////////////// COMPARE TO ///////////////////////////////////////////

  public static boolean testPlayerCompareTo() {
    boolean test1 = testCompareToDiffScore();
    boolean test2 = testCompareToSameScoreDiffName();
    boolean test3 = testCompareToEqual();
    if (!test1)
      System.out.print("diffScore FAIL ");
    if (!test2)
      System.out.print("diffName FAIL ");
    if (!test3)
      System.out.print("equals FAIL ");
    return test1 && test2 && test3;
  }

  /**
   * Tests compareTo when comparing players with different scores.
   * 
   * @return true if the method behaves as expected, false otherwise
   */
  private static boolean testCompareToDiffScore() {
    Player player1 = new Player("Alice", 1500);
    Player player2 = new Player("Bob", 1600);

    int result = player1.compareTo(player2);
    // should be positive Alice is greater
    return result < 0;
  }

  /**
   * Tests compareTo when comparing players with the same score but different names.
   * 
   * @return true if the method behaves as expected, false otherwise
   */

  private static boolean testCompareToSameScoreDiffName() {
    Player player1 = new Player("Alice", 1500);
    Player player2 = new Player("Bob", 1500);

    int result = player1.compareTo(player2);

    return result < 0;
  }

  /**
   * Tests compareTo when comparing players with the same score and name.
   * 
   * @return true if the method behaves as expected, false otherwise
   */

  private static boolean testCompareToEqual() {
    Player player1 = new Player("Alice", 1500);
    Player player2 = new Player("Alice", 1500);

    int result = player1.compareTo(player2);

    return result == 0;
  }

  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////

  public static boolean testNameLookup() {
    boolean test1 = testLookupRoot();
    boolean test2 = testLookupLeft();
    boolean test3 = testLookupRight();
    boolean test4 = testLookupNotPresent();
    if (!test1)
      System.out.print("lookupRoot FAIL ");
    if (!test2)
      System.out.print("lookupLeft FAIL ");
    if (!test3)
      System.out.print("lookupRight FAIL ");
    if (!test4)
      System.out.print("lookupNotPresent FAIL");
    return test1 && test2 && test3 && test4;
  }

  /**
   * Tests the lookup functionality when searching for the root node.
   * 
   * @return true if the root node is correctly returned, false otherwise
   */

  private static boolean testLookupRoot() {
    Player rootPlayer = new Player("Alice", 100);
    Leaderboard leaderboard = new Leaderboard();

    leaderboard.addPlayer(rootPlayer);
    Player result = leaderboard.lookup("Alice");
    return result != null && result.equals(rootPlayer);
  }

  /**
   * Tests the lookup functionality when searching for a left child node.
   * 
   * @return true if the left child node is correctly returned, false otherwise
   */

  private static boolean testLookupLeft() {
    // set root player
    Player rootPlayer = new Player("Alice", 100);
    BSTNode<Player> rootNode = new BSTNode<>(rootPlayer);
    Leaderboard leaderboard = new Leaderboard();
    leaderboard.addPlayer(rootPlayer);

    // set left child player
    Player leftPlayer = new Player("Bob", 50);
    BSTNode<Player> leftNode = new BSTNode<>(leftPlayer);
    leaderboard.getRoot().setLeft(leftNode);

    Player result = leaderboard.lookup("Bob");
    return result != null && result.equals(leftPlayer);

  }

  /**
   * Tests the lookup functionality when searching for a right child node.
   * 
   * @return true if the right child node is correctly returned, false otherwise
   */

  private static boolean testLookupRight() {
    // set root player
    Player rootPlayer = new Player("Alice", 100);
    Leaderboard leaderboard = new Leaderboard();
    leaderboard.addPlayer(rootPlayer);

    // set right child
    Player rightPlayer = new Player("Charlie", 150);
    BSTNode<Player> rightNode = new BSTNode<>(rightPlayer);
    leaderboard.getRoot().setRight(rightNode);

    Player result = leaderboard.lookup("Charlie");
    return result != null && result.equals(rightPlayer);
  }

  /**
   * Tests the lookup functionality when searching for a name that is not present in the tree.
   * 
   * @return true if the method correctly returns null for a non-existent name, false otherwise
   */

  private static boolean testLookupNotPresent() {
    // set root player
    Player rootPlayer = new Player("Alice", 100);
    Leaderboard leaderboard = new Leaderboard();
    leaderboard.addPlayer(rootPlayer);

    // set left child player
    Player leftPlayer = new Player("Bob", 50);
    BSTNode<Player> leftNode = new BSTNode<>(leftPlayer);
    leaderboard.getRoot().setLeft(leftNode);


    Player result = leaderboard.lookup("Charlie");
    return result == null;
  }

  //////////////////////////////////////////// ADD ////////////////////////////////////////////

  public static boolean testAdd() {
    boolean test1 = testAddPlayerEmpty();
    boolean test2 = testAddPlayer();
    boolean test3 = testAddPlayerDuplicate();
    if (!test1)
      System.out.print("addEmpty FAIL ");
    if (!test2)
      System.out.print("addPlayer FAIL ");
    if (!test3)
      System.out.print("addDuplicate FAIL ");
    return test1 && test2 && test3;
  }

  /**
   * Tests adding a player to an empty leaderboard.
   * 
   * @return true if the player is successfully added, the size is updated to 1, and the player can
   *         be looked up, false otherwise
   */
  private static boolean testAddPlayerEmpty() {
    Leaderboard leaderboard = new Leaderboard();
    Player player = new Player("Alice", 100);

    // Add Alice to the empty tree
    boolean result = leaderboard.addPlayer(player);
    // Check that the player was successfully added (size should be 1)
    if (result && leaderboard.size() == 1 && leaderboard.lookup("Alice") != null) {
      return true;
    }
    return false;
  }

  /**
   * Tests adding multiple players to the leaderboard.
   * 
   * @return true if all players are successfully added in the correct tree structure, the size is
   *         updated, and all players can be looked up, false otherwise
   */

  private static boolean testAddPlayer() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 70);
    Player player2 = new Player("Bob", 50);
    Player player3 = new Player("charlie", 80);


    leaderboard.addPlayer(player1); // Adding Alice to the empty tree
    leaderboard.addPlayer(player3);
    boolean result1 = leaderboard.addPlayer(player2); // Adding Bob to the non-empty tree

    // Check if the result is true and the size is updated
    if (!result1 || !(leaderboard.size() == 3)) {
      return false;
    }

    // Check if players exist in the tree using lookup
    Player lookupAlice = leaderboard.lookup("Alice");
    Player lookupBob = leaderboard.lookup("Bob");

    // Verify all players are present in the tree
    if (lookupAlice == null || lookupBob == null) {
      System.out.println("One or more players were not found in the tree.");
      return false;
    }
    if (!leaderboard.getRoot().getData().equals(player1)
        || !leaderboard.getRoot().getLeft().getData().equals(player2)
        || !leaderboard.getRoot().getRight().getData().equals(player3)) {
      return false;
    }

    // Ensure that the tree structure looks as expected
    System.out.println("Tree structure: ");
    System.out.println(leaderboard.prettyPrint());

    return true;
  }

  /**
   * Tests adding duplicate players to the leaderboard.
   * 
   * @return true if the duplicate player is not added, the size remains unchanged, and the tree
   *         structure is unaffected, false otherwise
   */

  private static boolean testAddPlayerDuplicate() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 70);
    Player player2 = new Player("Bob", 50);
    Player player3 = new Player("charlie", 80);


    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player3);

    // Try adding a duplicate player (Alice) again
    boolean result1 = leaderboard.addPlayer(player1);
    if (result1) {
      return false;
    }

    leaderboard.addPlayer(player2);
    // Verify that the size of the leaderboard is still 2 (Alice and Bob)
    if (leaderboard.size() != 3) {
      return false;
    }

    Player lookupAlice = leaderboard.lookup("Alice");
    Player lookupBob = leaderboard.lookup("Bob");
    if (lookupAlice == null || lookupBob == null) {
      return false;
    }

    System.out.println("Tree structure after adding duplicate: ");
    System.out.println(leaderboard.prettyPrint());
    if (!leaderboard.getRoot().getData().equals(player1)
        || !leaderboard.getRoot().getLeft().getData().equals(player2)
        || !leaderboard.getRoot().getRight().getData().equals(player3)) {
      return false;
    }

    // Output tree structure to verify everything is correct

    return true;
  }


  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////

  public static boolean testRemove() {
    boolean test1 = testRemoveLeaf();
    boolean test2 = testRemoveOneChild();
    boolean test3 = testRemoveTwoChildren();
    boolean test4 = testRemoveNotInTree();
    if (!test1)
      System.out.print("remove FAIL ");
    if (!test2)
      System.out.print("removeOneChild FAIL ");
    if (!test3)
      System.out.print("removeTwoChildren FAIL ");
    if (!test4)
      System.out.print("removeNotInTree FAIL ");
    return test1 && test2 && test3 && test4;
  }

  /**
   * Tests removing a player who is a leaf node (i.e., has no children).
   * 
   * @return true if the player is successfully removed, the size is updated correctly, and the tree
   *         remains valid
   */

  private static boolean testRemoveLeaf() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 50);
    Player player2 = new Player("Bob", 20);
    Player player3 = new Player("charlie", 70);

    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player2);
    leaderboard.addPlayer(player3);
    int initialSize = leaderboard.size();

    boolean removed = leaderboard.removePlayer(player3);
    int newSize = leaderboard.size();
    return removed && newSize == initialSize - 1;
  }

  /**
   * Tests removing a player who has one child.
   * 
   * @return true if the player is successfully removed, the size is updated correctly, and the tree
   *         remains valid
   */

  private static boolean testRemoveOneChild() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 80); // root
    Player player2 = new Player("Bob", 70); // left child
    Player player3 = new Player("charlie", 60); // child of left
    Player player4 = new Player("David", 90);// rigth child

    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player2);
    leaderboard.addPlayer(player3);
    leaderboard.addPlayer(player4);

    int initialCount = leaderboard.count();
    int initialSize = leaderboard.size();

    // Step 4: Remove player2 (Bob), who has one child (Charlie)
    boolean removed = leaderboard.removePlayer(player2);

    int newCount = leaderboard.count();
    int newSize = leaderboard.size();

    if (!removed || newCount != initialCount - 1 || newSize != initialSize - 1) {
      return false;

    }
    System.out.println("   ");
    System.out.println(leaderboard.prettyPrint());
    return true;
  }

  /**
   * Tests removing a player who has two children.
   * 
   * @return true if the player is successfully removed, the size is updated correctly, and the tree
   *         remains valid
   */

  private static boolean testRemoveTwoChildren() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 80); // root
    Player player2 = new Player("Bob", 70); // left child
    Player player3 = new Player("charlie", 60); // child of left
    Player player4 = new Player("David", 75);// rigth of left child
    Player player5 = new Player("Eva", 90);


    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player2);
    leaderboard.addPlayer(player3);
    leaderboard.addPlayer(player4);
    leaderboard.addPlayer(player5);

    int initialCount = leaderboard.count();
    int initialSize = leaderboard.size();

    // Step 4: Remove player2 (Bob), who has two child
    boolean removed = leaderboard.removePlayer(player2);

    int newCount = leaderboard.count();
    int newSize = leaderboard.size();

    if (!removed || newCount != initialCount - 1 || newSize != initialSize - 1) {
      return false;

    }
    System.out.println("   ");
    System.out.println(leaderboard.prettyPrint());
    return true;
  }

  /**
   * Tests removing a player who is not present in the leaderboard.
   * 
   * @return true if the method correctly identifies the player is not present and the tree remains
   *         unchanged
   */

  private static boolean testRemoveNotInTree() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 80); // root
    Player player2 = new Player("Bob", 70); // left child
    Player player3 = new Player("Charlie", 60);

    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player2);
    leaderboard.addPlayer(player3);

    Player playerNotInTree = new Player("Frank", 85);
    boolean removed = leaderboard.removePlayer(playerNotInTree);

    boolean test1 = !removed;
    boolean test2 = leaderboard.count() == 3;

    return test1 && test2;

  }
  //////////////////////////////////////////// GET NEXT ////////////////////////////////////////////

  public static boolean testGetNext() {
    boolean test1 = testGetNextAfterRoot();
    boolean test2 = testGetNextAfterLeftSubtree();
    boolean test3 = testGetNextAfterRightSubtree();
    if (!test1)
      System.out.print("afterRoot FAIL ");
    if (!test2)
      System.out.print("afterLeft FAIL ");
    if (!test3)
      System.out.print("afterRight FAIL ");
    return test1 && test2 && test3;
  }

  /**
   * Tests the `next(Player player)` method when the player is the root of the BST. Ensures that the
   * next higher score is correctly identified when starting from the root.
   *
   * @return true if the `next()` method correctly identifies the next higher player, false
   *         otherwise
   */
  private static boolean testGetNextAfterRoot() {
    Leaderboard leaderboard = new Leaderboard();

    // Step 2: Create and add players to the leaderboard
    Player player1 = new Player("Alice", 50); // Root player
    Player player2 = new Player("Bob", 25); // Smaller score
    Player player3 = new Player("Charlie", 70); // right-between score

    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player2);
    leaderboard.addPlayer(player3);

    // Step 3: Call next() for the player at the root (player1)

    Player nextPlayer = leaderboard.next(player1);

    System.out.println(leaderboard.prettyPrint());

    return nextPlayer.equals(player3);
  }

  /**
   * Tests the `next(Player player)` method when the player's next value is in the left subtree.
   * Validates that the method retrieves the correct player with the next higher score from the left
   * subtree.
   *
   * @return true if the `next()` method correctly identifies the next higher player, false
   *         otherwise
   */

  private static boolean testGetNextAfterLeftSubtree() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 50); // Root player with the highest score
    Player player2 = new Player("Bob", 30); // Player with score 45
    Player player3 = new Player("Charlie", 70); // Next higher player
    Player player4 = new Player("David", 25);


    // Add players to the leaderboard
    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player3);
    leaderboard.addPlayer(player2);


    Player nextPlayer = leaderboard.next(player4);
    return nextPlayer.equals(player2);
  }

  /**
   * Tests the `next(Player player)` method when the player's next value is in the right subtree.
   * Validates that the method retrieves the correct player with the next higher score from the
   * right subtree.
   *
   * @return true if the `next()` method correctly identifies the next higher player, false
   *         otherwise
   */

  private static boolean testGetNextAfterRightSubtree() {
    Leaderboard leaderboard = new Leaderboard();
    Player player1 = new Player("Alice", 50); // Root player with the highest score
    Player player2 = new Player("Bob", 30); // Player with score 45
    Player player3 = new Player("Charlie", 70); // Next higher player
    Player player4 = new Player("David", 65);

    leaderboard.addPlayer(player1);
    leaderboard.addPlayer(player3);
    leaderboard.addPlayer(player2);

    Player nextPlayer = leaderboard.next(player4);
    return nextPlayer.equals(player3);
  }

  //////////////////////////////////////////// MAIN ////////////////////////////////////////////

  public static void main(String[] args) {
    System.out.print("Player compareTo(): ");
    System.out.println(testPlayerCompareTo() ? "PASS" : "");

    System.out.print("Leaderboard lookup(): ");
    System.out.println(testNameLookup() ? "PASS" : "");

    System.out.print("Leaderboard add(): ");
    System.out.println(testAdd() ? "PASS" : "");

    System.out.print("Leaderboard remove(): ");
    System.out.println(testRemove() ? "PASS" : "");

    System.out.print("Leaderboard next(): ");
    System.out.println(testGetNext() ? "PASS" : "");
  }

}
