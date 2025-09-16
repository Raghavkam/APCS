import javax.swing.*;
import java.awt.*;

public class BouncingSquareDriver {

   public static void main(String[] args) { 
      
      JFrame frame = new JFrame("Animation!");
      frame.setSize(525, 500);  //This time we're going for a 500x500 panel
      frame.setLocation(20, 20);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new BouncingSquarePanel());
      frame.setVisible(true);   
   }
}