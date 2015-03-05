package com.eloratinsystem;

public class WinExpectancy
{
  protected static double exptdResult = 0;

  public static double getExptdResult(double dr)
    {      
      exptdResult = (1/((Math.pow(10,(-dr/400)))+1));
      return exptdResult;
    }
  
}//end class