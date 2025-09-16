import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

class AnimationPanel extends JPanel
{
   //fields
   
   //Start with two static final constants.  "final" is a keyword that
   //means you can't ever change a value.  Use them when you want to access
   //but not modify a certain value many separate methods will need to know.
   //First, a constant to represent how wide & tall the animation panel
   //will be.  Match with the driver - the JFrame should be FRAME pixels
   //wide and FRAME + 25 pixels tall, on a PC.
   public static final int FRAME = 500;
   //Second, decide on a background color.
   private static final Color BACKGROUND = new Color(204, 204, 204);
   
   //Now let's make the fields we'll need.
   //Instead of using paintComponent() to draw everything directly, we'll
   //store each frame of animation in an image - this improves speed.
   //We'll use a graphics object to modify the image.  We need both fields.
   private BufferedImage myImage;  
   private Graphics myBuffer;
   
   //We'll need a Timer object to trigger animation - this is just an
   //object that calls a certain method over and over again after a given
   //time interval.  We'll use it to make & draw each animation frame.
   private Timer t;
   
   //An ArrayList of objects that implement the Animatable interface.
   //Anything we want to move around the screen we'll add to this data
   //structure.
   private ArrayList<Animatable> animationObjects;
   private int fcount;
   
   
     
   //constructors
   
   //We just need one constructor - initialize all the fields and start the animation!
   public AnimationPanel()
   {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB); //Make the image that will store each frame
      myBuffer = myImage.getGraphics(); //Get the Graphics object we can use to manipulate the image
      myBuffer.setColor(BACKGROUND);    //The next two lines draw the background rectangle for the first time
      myBuffer.fillRect(0,0,FRAME,FRAME);
     
      
      animationObjects = new ArrayList<Animatable>();  //Initialize the Animatable ArrayList
     
      Trapezoid cr = new Trapezoid(0, 500, 500,200, new Color(13, 149, 171), 1,1);
     
      animationObjects.add(cr);

     
      t = new Timer(5, new AnimationListener());  //A Timer takes two arguments - a time delay (in milliseconds) and an object
                                                  //The object must implement an interface - ActionListener - that demands
                                                  //a single method: actionPerformed().  This class is going to be defined
                                                  //within the AnimationPanel class - look below for "private classes".
                                                  //The Timer waits the given time delay, then calls actionPerformed(), over
                                                  //and over.  It begins when you call .start() and ends when you call .stop().
      t.start();  //Starts the timer
   }
   
   
   //no accessor or modifier methods; we won't need to access this class's fields from anywhere external to this class
   
   
   //overridden methods
   
   public void paintComponent(Graphics g)  //Required method to paint to the screen
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
        }
   
   
   
   //instance methods
   
   public void animate()
   {
      //This performs the next animation step by drawing to myImage using commands on myBuffer
      //Then, we'll call repaint(), which calls paintComponent(), which draws myImage to the screen
     
      //Clear the current state of myImage by writing over it with a new blank background
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
     
      //Loop through the ArrayList of Animatable objects; do an animation step on each one & draw it
      for(Animatable animationObject : animationObjects)
      {
         
         animationObject.step();  //Every Animatable object knows how to do one animation step
         animationObject.drawMe(myBuffer);  //Every Animatable object knows how to draw itself on a Graphics object
        
         
         
      }
     
      //Call built-in JFrame method repaint(), which calls paintComponent, which puts the next frame on screen
      repaint();
   }
 
   //private classes
   
   private class AnimationListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)  //Gets called over and over by the Timer
      {
         animate();  //...hence animation!
      }
   }
}