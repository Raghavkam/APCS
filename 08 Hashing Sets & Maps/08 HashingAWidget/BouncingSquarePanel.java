import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

class BouncingSquarePanel extends JPanel {
 
   public static final int FRAME = 500;
   private static final Color BACKGROUND = new Color(204, 204, 204); 
   private BufferedImage myImage;  
   private Graphics myBuffer;
   private Timer t;
   private ArrayList<Animatable> animationObjects;
   private int fcount; 
   
   public BouncingSquarePanel() {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      
      animationObjects = new ArrayList<Animatable>();
      
      Trapezoid a = new Trapezoid(50, 270, 300,30 ,Color.RED);
      animationObjects.add(a); 
      

      t = new Timer(5, new AnimationListener());
      t.start();
   }
   
   public void paintComponent(Graphics g) {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   
   public void animate() {
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      for(Animatable animationObject : animationObjects) {
         animationObject.step();  
         animationObject.drawMe(myBuffer);  
      }
      repaint();
   }
   private class AnimationListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         animate();  
      }
   }
}