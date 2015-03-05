  Within the World Football Elo rating system there are two fundamental formulas. The first is to calculate the new ranking of a team and the second is to calculate that teams win expectancy (We). The win expectancy is necessary to complete the new Elo ranking formula.

The formula for calculating a team’s new ranking is:


 Rn = Ro + KG × (W - We)


Rn is the new ranking, 

Ro is the old (pre-match) ranking,
 
K is the weight constant or K-factor assigned,

G is a number from an index of goal differences. This index value is entered into the code using these values; If the game is a draw or won by one goal then G is equal to 1, if the game is won by two goals then G is equal to 1.5 and if the game is won by three or more goals then where N is the goal difference G is equal to (11+N)/8. 

W is the result of the game (1 for a win, 0.5 for a draw, and 0 for a loss). 80 points were also added for home advantage.

We is the win expectancy, calculated from the following formula where dr is the difference in ratings:

   
We = 1 / (10(-dr/400) + 1)


  An example would be if Arsenal were to play Manchester United at home and Arsenal’s current Elo ranking is 1841 and Man Utd's ranking is 1823. If the game were to finish Arsenal 3 Man Utd 0 with both teams having a k-factor weight of 40 as both are top table teams based on the constants below, then the calculator would predict that Arsenal had a 53% chance of winning giving them a new ranking of 1874 while Man Utd had a 47% chance of winning giving them a new ranking of 1789.

topTableTeam plays topTableTeam = 40
topTableTeam plays midTableTeam = 20
topTableTeam plays bottomTableTeam = 30
midTableTeam plays midTableTeam  = 10
midTableTeam plays bottomTableTeam = 20
bottomTableTeam plays bottomTableTeam = 40

Note: topTableTeams = top 6 teams, midTableTeam = the next 8 & bottomTableTeam = bottom 6 teams.
