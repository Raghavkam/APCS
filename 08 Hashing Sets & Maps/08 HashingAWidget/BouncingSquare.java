import javax.swing.*;
import java.awt.*;

public class BouncingSquare extends Square implements Animatable {
   
   private int dx;
   private int dy;
   
   public BouncingSquare(int side, int x, int y, Color c, int dxVal, int dyVal) {
      super(side, x, y, c);
      dx = dxVal;
      dy = dyVal;
   } 

   public int getdx() {
      return dx;
   }
   public int getdy() {
      return dy;
   }
   
   public void setdx(int dxValue) {
      dx = dxValue;
   }
   public void setdy(int dyValue) {
      dy =  dyValue;
   }
   
   public void step() {
      if(getX() <= 0) {
         dx = dx * -1;       
      }
      
      if (getY() <= 0) {
         dy = dy * -1;
      }
      
      if(getX() >= (500 - getSide())) {
         dx = dx * -1;
      }
      
      if(getY() >= (500 - getSide())) {
         dy = dy * -1;
      }
      setX(getX() + dx);
      setY(getY() + dy);
   }
   
}