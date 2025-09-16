import javax.swing.*;
import java.awt.*;

/*-------Give to the students----------*/

public class Trapezoid implements Animatable
{
   private int Bx;  //upper left x
   private int By;  //upper left y
   private int Cx;  //upper right x
   private int Cy;  //upper right y
   private int newx;
   private int newy;
   private Color c;
   

   public Trapezoid(int ulx, int uly, int urx, int ury, Color col, int colx, int coly)
   {
      Bx = ulx;
      By = uly;
      Cx = urx;
      Cy = uly;
      newx = colx;
      newy = coly;
      c = col; 
   }

   public int getBx(){
      return Bx;}
   public int getBy(){
      return By;}
   public int getCx(){
      return Cx;}
   public int getCy(){
      return Cy;}
//
   public void setBx(int ulx)
   {Bx = ulx;}
   public void setBy(int uly)
   {By = uly;}
   public void setCx(int urx)
   {Cx = urx;}
   public void setCy(int ury)
   {Cy = ury;}
   
   public void step() {
      setBx(getBx()+newx);
      setCx(getCx()-newx);
      setBy(getBy()-newy);
     
      setCy(getCy()-newy);

      
      
    }

//
//

   public void drawMe(Graphics g)
   {
      int[] xs = new int[]{0, Bx, Cx, 500};
      int[] ys = new int[]{500, By, Cy, 500};
      g.setColor(c);
   
      g.fillPolygon(xs, ys,4);  
   }
}