package ticTacToe;

import java.util.Scanner;
import java.util.Random;


public class TicTacToe {

 static char[] [] board = new char[3][3];
 static Scanner input=new Scanner(System.in);
 static Random randGen = new Random();  // New random number generator


 //Object of Stats class to maintain statistics
 static Stats stat = new Stats();  

 /**
  * Prints the TicTacToe board
  * @param arr: The board so far
  */
 public static void printBoard(char [][] arr){
  System.out.println();
  for (int i=0; i<3; i++)
  {
   for (int j=0; j<3; j++)
   {
    System.out.print(arr[i][j]);
    if(j!=2)

     //Print the | for readable output
     System.out.print(" " + "|" + " ");    
   }
   System.out.println();
   if(i!=2) {
    System.out.print("_   _   _ ");    // Print _ for readability
    System.out.println();;
   }
  }
 }
 
 /**
  * Clear the TicTacToe board before starting a new game
  * @param arr: The board so far
  */
 public static void clearBoard(char [][] arr){
  for (int i=0; i<3; i++)
  {
   for (int j=0; j<3; j++)
   {
    arr[i][j] = ' ';   
   } 
  }
 }
 
 /** Determines if the player with the specified token wins
  * 
  * @param symbol: Specifies whether the player is X or O
  * @return true if player has won, false otherwise
  */
   public static boolean isWon(char symbol) {
    
     for (int i = 0; i < 3; i++){ //horizontal
       if (board[i][0] == symbol 
           && board[i][1] == symbol
           && board[i][2] == symbol) {
         return true;
       }
       else if (board[0][i] == symbol //vertical
           && board[1][i] == symbol
           && board[2][i] == symbol) {
         return true;
       }
     }

     if (board[0][0] == symbol   //top-left to bot-right diagonal
           && board[1][1] == symbol
           && board[2][2] == symbol) {
         return true;
       }  
     else if (board[2][0] == symbol  //bot-left to top-right diagonal
           && board[1][1] == symbol
           && board[0][2] == symbol) {
         return true;
     }

     return false;
   }

  
   public static boolean isOccupied(int row, int col){

   if(board[row][col] == 'X' || board[row][col] == 'O'){
    return true;
   }
   return false;
   }
   
   public static int whoStarts(){
     int coinToss = 0;

     coinToss = randGen.nextInt(2); // Determine who goes first
   
   return coinToss;
   }
   
   /** takes care of the human's move
    * 1. Prompt for a cell, then column
    * 2. Puts a symbol (X or O) on the board
    * 3. Prints the updated board
    * 4. If a human wins: prints, updates stats and returns true
    * 5. If not a win yet, returns false */
   
   public static boolean humanTurn(char symbol){
    
    System.out.print("\n\nEnter your move: (row column): " );
   int row = input.nextInt();
   int col = input.nextInt();
   
   if(!isOccupied(row,col)){
   board[row][col] = symbol;
   }
   else{
    while(isOccupied(row,col)){   
      System.out.print("\n\nSpace taken. Enter your move: (row column): " );
     row = input.nextInt();
     col = input.nextInt();
    }
    board[row][col] = symbol;
   }

   printBoard(board);

   if(isWon(symbol)){
    System.out.println("\n\nUser wins!");
    stat.incrementUserWins();
    return true;
   }
   return false;
   } 
   
   /** takes care of the computer's move
    * 1. Generates numbers until finds an empty cell
    * 2. Puts a symbol (X or O) on the board
    * 3. Prints the updated board
    * 4. If a comp wins: prints, updates stats and returns true
    * 5. If not a win yet, returns false */
   
   public static boolean compTurn(char symbol) {
     
   int row = randGen.nextInt(3);
   int col = randGen.nextInt(3);
   
   if(!isOccupied(row,col)){
    System.out.println("\n\nMy Move is: "+row+" "+ col);
    board[row][col] = symbol;
   }
   else{
    while(isOccupied(row,col)){   
     row = randGen.nextInt(3);
     col = randGen.nextInt(3);
    }
    System.out.println("\n\nMy Move is: "+row+" "+ col);
    board[row][col] = symbol;
   }

   printBoard(board);

   if(isWon(symbol)){
    System.out.println("\n\nComputer wins!");
    stat.incrementComputerWins();
    return true;
   }
   return false;
   }
   
   /** If human goes first:
    * We have 9 moves in total (max). 8 moves will be in a loop
    * and the last human move is outside of the loop:
    * 1. human goes first, with a X
    * 2. If the returned value is true (human won), then boolean flag=true
    *    and we break out of the loop. done indicates that the game is over.
    * 3. If the game is not over, then it is computer's turn. 
    * 4. If the returned value is true (comp won), then boolean flag=true
    *    and we break out of the loop. done indicates that the game is over
    * 5. Repeat the two steps above 3 more times. 
    * 6. If the done is still false, then a human performs one more move and
    * we check if the move led to the win or tie.    
    * */
   
   public static void humanFirst(){
   boolean done=false;

    for (int i=0; i<4; i++) { 
    if (humanTurn('X')) {
     done=true;
     break;
    }
    if (compTurn('O')){
     done=true;
     break;
    }
     }  //end of for loop;
      if (!done){
       if (!humanTurn('X')) {
        System.out.println("\n\nA tie!");
        stat.incrementTies();
       }
   }
   }
   
   /**
    * Same logic as above, only the first computer's move happens before
    * the loop. We do not need to check for winning combination here, since
    * comp can't win after one move. 
    * After the loop we check if the game is done. If not, report a tie and
    * update statistics.
    */
   
   public static void compFirst(){
     
     boolean done=false;

       compTurn('X');

    for (int i=0; i<4; i++) { 
    if (humanTurn('O')) {
     done=true;
     break;
    }
    if (compTurn('X')){
     done=true;
     break;
    }
     }  //end of for loop;
      if (!done){
       System.out.println("\n\nA tie!");
       stat.incrementTies();
   }
    
   }
   
 public static void main(String[] args) {
  
  // input from the user, if he wants to play another game
  String playAgain=""; 

  // input from the user, if he wants to clear stats
  String clearStats=""; 
  
  do {      //play until 'n' is pressed
  clearBoard(board);   //clear the baord

  //Generate Random Assignment, determines who goes first;
  int move = whoStarts();
  if (move == 0) {
   System.out.println("\nI start first. I choose X and you get 0");
   compFirst();
  }
  else{ 
   System.out.println("\nYou start first. You get X and I get 0");
   humanFirst(); 
  }
  
  //Print statistics and ask if a user wants to repeat a game
  stat.printStats();
  System.out.println("\nPress 'y' to play again or 'n' to quit.");
   playAgain = input.next();
  if(playAgain.charAt(0) == 'y'){
   System.out.println("\nPress 'y' to clear statistics or 'n' to continue without clearing.");
   clearStats = input.next();
   if(clearStats.charAt(0) == 'y'){
    stat.reset();
   }
  }
  // If user enters 'y', ask to clear statistics
        //  if user enters 'y', clear statistics and restart the game
        //If user enters 'n', continue without clearing
  // //If user enters 'n', quit the game
     
   
  } while(playAgain.charAt(0)!='n'); //done with the outer loop
     
     System.out.println("\nBye, see you later!");
 }
}
