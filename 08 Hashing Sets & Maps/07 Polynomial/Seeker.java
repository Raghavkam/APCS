import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;

public class Seeker extends Athlete
{
   private int count;
   public Seeker()
   {
   super(1, 1, Display.NORTH, 0);

   }
   

   
   public void seek()
   {

     while(!leftIsClear())
      {
        count++;
        move();
        if(nextToABeeper())
         {
          pickBeeper();
         }
      }
 
      turnRight();
      move();
      turnRight();  
     
     
      for (int i = 0; i < (count / 2) - 1; i++){
            for (int i = 0; i < count; i++){
               move();
               if(nextToABeeper()){
                  pickBeeper();
               }
            }
         }
         
      turnLeft();
      move();
      if(nextToABeeper())
         {
          pickBeeper();
         }
      turnLeft();

      for (int i = 0; i < count; i++)
      {
      move();
        if(nextToABeeper())
         {
          pickBeeper();
         }
      }

      turnRight();
      move();
       if(nextToABeeper())
         {
          pickBeeper();
         }
      turnRight();
        
      
         
      for(int i = 0; i < count; i++){
         move();
         if(nextToABeeper()){
            pickBeeper();
         }
      }
     
     
      if(frontIsClear())
      {
         turnLeft();
         while(frontIsClear())
         {
            move();
         }
         turnLeft();
         while(frontIsClear())
         {
            move();
         }
         putBeeper();
      }
      else
      {
      turnRight();
         while(frontIsClear())
         {
            move();
         }  
         putBeeper();  
     
      }
 
     
         
   
    }
   
   
    public void goBruh()
    {
      for (int i = 0; i < count; i++)
      {
      move();
         if(nextToABeeper())
         {
          pickBeeper();
         }
      }
     
      turnLeft();
      move();
      if(nextToABeeper())
         {
          pickBeeper();
         }
      turnLeft();

      for (int i = 0; i < count; i++)
      {
      move();
        if(nextToABeeper())
         {
          pickBeeper();
         }
      }

      turnRight();
      move();
       if(nextToABeeper())
         {
          pickBeeper();
         }
      turnRight();
    }
}