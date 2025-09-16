import javax.swing.*;
import java.awt.*;

public class Circle
{

   private int r;
   private int x;  
   private int y; 
   private Color c;
   
   public Circle()
   {
      r = 20;
      x = 200;
      y = 20;
      c = Color.WHITE;
   }
   public Circle(int xValue, int yValue, int rValue, Color cValue)
   {
      r = rValue;
      x = xValue;
      y = yValue;
      c = cValue;
   }
   public Circle(Square s)
   {
      r = s.getSide();
      x = s.getX() + s.getSide()/2 ;
      y = s.getY() + s.getSide()/2;
      c = s.getColor().darker();
   }

   public int getRadius()
   {
      return r;
   }
   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
   }
   public Color getColor()
   {
      return c;
   }

   public void setRadius(int rValue)
   {
      r = rValue;
   }
   public void setX(int xValue)
   {
      x = xValue;
   }
   public void setY(int yValue)
   {
      y = yValue;
   }
   public void setColor(Color cValue)
   {
      c = cValue;
   }
   

   public void drawMe(Graphics g)
   {
      g.setColor(c);
      g.fillOval(x - (r/2), y - (r/2), r, r);
   }
}