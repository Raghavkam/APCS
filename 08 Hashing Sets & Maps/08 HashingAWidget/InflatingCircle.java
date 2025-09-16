import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

//InflatingCircle extends YOUR Circle class and implements Animatable

//Animatable requires a step() method, which is in this file, and a
//drawMe(Graphics g) method, which you should already have in Circle

public class InflatingCircle extends Circle implements Animatable
{
   private int dR;  //int to store how much r (radius) will change in next animation step
   private int minR;  //smallest value we want r to be
   private int maxR;  //largest value we want r to be
      
   // constructors
   public InflatingCircle()
   {
      super(10, 250, 250, Color.BLACK);
      dR = 3;
      minR = 10;
      maxR = 200;
   }
   
   public InflatingCircle(int x, int y, int radius, Color color, int dRVal, int minRVal, int maxRVal) {
      super(x, y, radius, color);
      dR = dRVal;
      minR = minRVal;
      maxR = maxRVal;
   }
   
   //accessors
   public int getDR()
   {
      return dR;
   }
   public int getMinR()
   {
      return minR;
   }
   public int getMaxR()
   {
      return maxR;
   }
   
   //modifiers
   public void setDR(int dRValue)
   {
      dR = dRValue;
   }
   public void setMinR(int minRValue)
   {
      minR = minRValue;
   }
   public void setMaxR(int maxRValue)
   {
      maxR = maxRValue;
   }
      
   //instance methods
   public void step()  //Implement Animatable's required step()
   {
      //Check to see if our circle is too small
      //If so, make sure dR is positive (radius is increasing)
      if(getRadius() < minR)
      {
         if(dR < 0)
         {
            dR *= -1;
         }
      }
      
      //Check to see if our circle is too big
      //If so, make sure dR is negative (radius is decreasing)
      if(getRadius() > maxR)
      {
         if(dR > 0)
         {
            dR *= -1;
         }
      }
      setRadius(getRadius() + dR);  //Change the radius a bit - either out or in - for each animation step
   }
   
}