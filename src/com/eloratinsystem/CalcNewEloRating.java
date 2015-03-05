package com.eloratinsystem;

import java.text.DecimalFormat;

public class CalcNewEloRating
{     
    protected double initialRating1, initialRating2; 
    protected int k_factor;
    protected int goalDiff; 				//goalDiff is the goal difference not the goal diff index
    protected double resultA, resultB;				//result is the result of the game whereas a win = 1, a draw = 0.5 & a loss = 0.
    protected double diffRate, pointsAvailable, longDecimal;    
   
  /*************** K-factor weighted index ***********
   * topTableTeams = top 6 teams, midTableTeam = the next 8 & bottomTableTeam = bottom 6 teams. 
   * 
   *	topTableTeam plays topTableTeam = 40
   *	topTableTeam plays midTableTeam = 20
   *	topTableTeam plays bottomTableTeam = 30
   *	midTableTeam plays midTableTeam  = 10
   *	midTableTeam plays bottomTableTeam = 20
   *	bottomTableTeam plays bottomTableTeam = 40
   *   
   ***************************************************/     
   
    /////////////Default Constructor////////////////////////////

   public CalcNewEloRating()
   {
  	initialRating1 = 0;
  	initialRating2 = 0;
  	k_factor = 0;
  	goalDiff = 0;
  	resultA = 0;
  	resultB = 0;
   }

   /////////////User-defined Constructor///////////////////////
  
   public CalcNewEloRating (double Ro_A, double Ro_B, int k, int g, double rA, double rB)		
   {  	
	initialRating1 = Ro_A;
	initialRating2 = Ro_B;
	this.k_factor = k;//////////////////////////////////////////////////////////////////this
	goalDiff = g;
	resultA = rA;
	resultB = rB;
   }
 
   /////////////Setter Access Methods/////////////
   
   public void setInitialRating1(int Ro_A)
   {
  	initialRating1 = Ro_A;
   }
   
   public void setInitialRating2(int Ro_B)
   {
  	initialRating2 = Ro_B;
   }
   
   public void setK_factor(int k)
   {
	k_factor = k;
   }
   
   public void setGoalDiff(int g)
   {
	goalDiff = g;
   }   
   
   public void setResultA(double rA)
   {
	resultA = rA;
   }
   
   public void setResultB(double rB)
   {
	resultB = rB;
   }
   
   public void setDiffRate(double dr)
   {
	diffRate = dr;
   }

   ////////////////////// Getter Access Methods /////////////////////////
   
   public double getInitialRating1()
   {
    return initialRating1;					//-100; subtract a home advantage value here to return to correct pre-match rating 
   }
   
   public double getInitialRating2()
   {
    return initialRating2;
   }
   
   public int getK_factor()
   {
	return k_factor;
   }
   
   public double getResultA()
   {
    return resultA;
   }
   
   public double getResultB()
   {
    return resultB;
   }
   
   ///////////////////// Calc Goal Difference //////////////////////////
   
   public double getGoalDiff()
   {
	//This method returns a number from the index of goal difference to the new rating equation i.e getNewRating()method below
    if (goalDiff <= 1)		//If the game is a draw or is won by one goal then 1 is returned
	   		return 1;
    else if (goalDiff == 2)		//If the game is won by two goals then 1.5(3/2) is returned
    		return 1.5;    
    else 	return((11+ (double)goalDiff)/8);		//If the game is won by three or more goals then (11 + the actual goal difference) divided by 8 is returned   
   }
   
   ////////////////// Calc Difference in Rating ////////////////////////
   //This method returns the difference in ratings between two teams in a game.
   public double getDiffRate()
   {
	if (initialRating1 > initialRating2)					//if rating1 is greater than rating2 then return that difference
			return (initialRating1-initialRating2);
	else if (initialRating2 > initialRating1)				//else if rating2 is greater than rating1 then return that difference
			return(initialRating2-initialRating1);
	else 	return 0;    														//otherwise they are both equal so return zero
   }

   ///////////////////// Calc New Rating ////////////////////////////////
   
   public double getNewRatingTeamA()		
   {
	return (initialRating1+(k_factor*getGoalDiff()*(resultA-WinExpectancy.getExptdResult(getDiffRate()))));
	//subtract home advantage value here for correct new rating use this --->   return ((initialRating1-100)+(k_factor*getGoalDiff()*(resultA-WinExpectancy.getExptdResult(getDiffRate()))));
   }
   
   public double getNewRatingTeamB()		
   {
	return initialRating2+(k_factor*getGoalDiff()*(resultB-(1-WinExpectancy.getExptdResult(getDiffRate()))));	
   }
   
   /////////////////// Calc Points Available ////////////////////////////////

   public double getAvailablePointsTeamA()		
   {	
    return (k_factor*getGoalDiff()*(resultA-WinExpectancy.getExptdResult(getDiffRate())));
   }
   
   public double getAvailablePointsTeamB()		
   {	
    return (k_factor*getGoalDiff()*(resultB-(1-WinExpectancy.getExptdResult(getDiffRate()))));
   }
   
   ///////////////////// Display Methods /////////////////////////////////
   
   public void display()
   {
	System.out.print("\n\tThe K-factor is:\t\t" + k_factor);
	System.out.print("\n\tGoal difference index:\t\t" + getGoalDiff());		   
	System.out.print("\n\tDifference in ratings:\t\t" + getDiffRate());	
   }
 
   //Win expectancy display methods for both teams rounded off to 1 decimal place
   //this is calculated for team A
   public void displayTeamA()
   {
	if(initialRating1 > initialRating2)
	longDecimal = (WinExpectancy.getExptdResult(getDiffRate())*100);//This will be the higher win expectancy if Team A (Home) is greater than Team B (Away).	
	else longDecimal = ((1-WinExpectancy.getExptdResult(getDiffRate()))*100); //else it will be the lowest win expectancy.
	
	DecimalFormat df = new DecimalFormat("#.##");
	System.out.print("\n\tWin Expectancy for Team A:\t" + WinExpectancy.getExptdResult(getDiffRate())+"\n");
    System.out.print("\n\tChances of Team A winning:\t" + (df.format(longDecimal)) + "%");
    System.out.print("\n\tPre-match Elo rating:\t\t" + getInitialRating1());
    System.out.print("\n\tNew Elo rating:\t\t\t" + getNewRatingTeamA());
    System.out.print("\n\tPoints change:\t\t\t" + getAvailablePointsTeamA());
   }   
 
   //this subtracts team A's win expectancy to obtain team B's win expectancy (i.e. it subtracts from 1 and multiplies by 100).
   public void displayTeamB()
   {
	if(initialRating1 < initialRating2)
	longDecimal = (WinExpectancy.getExptdResult(getDiffRate())*100);//This will be the higher win expectancy if Team B (Away) is greater than Team A (Home).			
	else longDecimal = ((1-WinExpectancy.getExptdResult(getDiffRate()))*100); //else it will be the lowest win expectancy.
	
	DecimalFormat df = new DecimalFormat("#.##");
	System.out.print("\n\tWin Expectancy for Team B:\t" + (1-WinExpectancy.getExptdResult(getDiffRate()))+"\n");
	System.out.print("\n\tChances of Team B winning:\t" + (df.format(longDecimal)) + "%");
	System.out.print("\n\tPre-match Elo rating:\t\t" + getInitialRating2());
	System.out.print("\n\tNew Elo rating:\t\t\t" + getNewRatingTeamB());
	System.out.print("\n\tPoints change:\t\t\t" + getAvailablePointsTeamB());
   }   
}//end class

