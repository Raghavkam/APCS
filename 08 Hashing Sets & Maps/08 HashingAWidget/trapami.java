import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class trapami extends Trapezoid implements Animatable{
private int dX;
   private int dY;
   
   
   //constructors
   public trapami(int ulx, int uly, int urx, int ury, Color co, int xchg, int ychg)
   {
     
     super(ulx, urx, uly, ury,co);
     dX = xchg;
     dY = ychg;
     
   }
   
   //instance methods
   public void step()
   {
      setBx(getBx()+dX);
      setCx(getCx()-dX);
      setBy(getBy()-dY);
     
      setCy(getCy()-dY);
   
   
           
   }
   
   //other useful Java methods
   
   //(leave this commented out until the assignment tells you to use it)
   


}