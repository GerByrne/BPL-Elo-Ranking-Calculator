package com.eloratinsystem;

class EloRatingTester
{   
  static double rating1;			 
  static double rating2;   
  static double resultA;       		//This is Team A's value for a result whereas a win = 1, a draw = 0.5 & a loss = 0.
  static double resultB;			//This is Team B's value for a result.
  static int k1; 					//k1 is the k-factor for this game(topTableTeam plays bottomTableTeam) 
  static int gd;					//gd is the value for goal difference & initialized here (i.e. if the game results as 4:2 then 2 is entered here)
   
  public static void main(String[] args)
  {
   //Using Keyboard.readInt as an example for a manual update mechanism   
   System.out.println("\tEnter Elo Ranking for Team A (Home)\t"); rating1 = Keyboard.readDouble();			/********** +100; //adding a value for home advantage ************/
   System.out.println("\n\tEnter Elo Ranking for Team B (Away)\t"); rating2 = Keyboard.readDouble();
   System.out.println("\n\tEnter K-factor\t"); k1 = Keyboard.readInt();
   System.out.println("\n\tEnter Goal the difference\t"); gd = Keyboard.readInt();			//This asks for the actual goal difference NOT the index value for the goal difference
   System.out.println("\n\tEnter The value for the Team A's match result (win = 1 draw = 0.5, loss = 0).\t"); resultA = Keyboard.readDouble();
   System.out.println("\n\tEnter The value for the Team B's match result (win = 1 draw = 0.5, loss = 0).\t"); resultB = Keyboard.readDouble();
	  
   //Using the User-defined Constructor
   CalcNewEloRating TeamA = new CalcNewEloRating(rating1, rating2, k1, gd, resultA, resultB);						
   CalcNewEloRating TeamB = new CalcNewEloRating(rating1, rating2, k1, gd, resultA, resultB);	
      
   System.out.println("\n\tTeam A (Home)");
   TeamA.display();
   TeamA.displayTeamA();      
   
   System.out.println("\n\t----------------------------------------------------\n");	  
   
   System.out.println("\tTeam B (Away)");
   TeamB.display(); 
   TeamB.displayTeamB();     
   
   System.out.println("\n\t----------------------------------------------------\n");   
  }  

}//end class