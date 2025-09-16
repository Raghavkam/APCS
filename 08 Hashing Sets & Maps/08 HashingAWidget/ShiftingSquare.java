import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class ShiftingSquare extends Square implements Animatable {
   
   private int dX;
   private int minX;
   private int maxX;
   
   public ShiftingSquare() {
      super(100, 250, 250, Color.WHITE);
      dX = 5;
      minX = 10;
      maxX = 300;
   }
   
   public ShiftingSquare(int x, int y, int side, Color color, int dXValue, int minXValue, int maxXValue) {
      super(side, x, y, color);
      dX = dXValue;
      minX = minXValue;
      maxX = maxXValue;
   }
   
   public int getdX() {
      return dX;
   }
   public int getminX() {
      return minX;
   }
   public int getmaxX() {
      return maxX;
   }
   
   public void setdX(int dXVal) {
      dX = dXVal;
   }
   public void setminX(int minXVal) {
      minX = minXVal;
   }
   public void setmaxX(int maxXVal) {
      maxX = maxXVal;
   }
   
   public void step() {
      if(getX() < minX)
      {
         if(dX < 0)
         {
            dX *= -1;
         }
      }
      
      if(getX() > maxX)
      {
         if(dX > 0)
         {
            dX *= -1;
         }
      }
      setX(getX() + dX);  
   }
}
