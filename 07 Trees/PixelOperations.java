//
// Torbert, 24 July 2013
//
import java.awt.Color;
import java.awt.image.BufferedImage;
//
public class PixelOperations
{
   public Color[][] getArray(BufferedImage img)
   {
      Color[][] arr;
   	//
      int numcols = img.getWidth();
      int numrows = img.getHeight();
   	//
      arr = new Color[numrows][numcols];
   	//
      for(int j = 0; j < arr.length; j++)
      {
         for(int k = 0; k < arr[0].length; k++)
         {
            int rgb = img.getRGB(k,j);
         	//
            arr[j][k] = new Color(rgb);
         }
      }
   	//
      return arr;
   }
   public void setImage(BufferedImage img, Color[][] arr)
   {
      for(int j = 0; j < arr.length; j++)
      {
         for(int k = 0; k < arr[0].length; k++)
         {
            Color tmp = arr[j][k];
            //
            int rgb = tmp.getRGB();
            //
            img.setRGB(k,j,rgb);
         }
      }
   }
   //
	/**********************************************************************/
	//
	// pixel operations
	// 
   public void zeroBlue(Color[][] arr)
   {
      for(int j = 0; j < arr.length; j++) // arr.length = num of rows
      {
         for(int k = 0; k < arr[0].length; k++) // arr[0].length = num columns
         {
            Color tmp = arr[j][k]; // accessing a spot and chagnign the color
            arr[j][k] = new Color( tmp.getRed(), tmp.getGreen(), 0 );// get red and green from the original, and change the blue to 0
         }
      }
   }
   public void sunsetize(Color[][] arr)
   {
      for(int j = 0; j < arr.length; j++) // arr.length = num of rows
      {
         for(int k = 0; k < arr[0].length; k++) // arr[0].length = num columns
         {
            Color tmp = arr[j][k]; // accessing a spot and chagnign the color
            int red = tmp.getRed();
            double green = 0.8*tmp.getGreen();
            double blue = 0.8*tmp.getBlue();
            arr[j][k] = new Color( red, (int)green, (int)blue );// get red and green from the original, and change the blue to 0
         }
      }
   }
   //
   public void negate(Color[][] arr)
   {
      for(int j = 0; j < arr.length; j++) // arr.length = num of rows
      {
         for(int k = 0; k < arr[0].length; k++) // arr[0].length = num columns
         {
            Color tmp = arr[j][k]; // accessing a spot and chagnign the color
            double red = 255 - tmp.getRed();
            double green = 255 - tmp.getGreen();
            double blue = 255 - tmp.getBlue();
            arr[j][k] = new Color( (int)red, (int)green, (int)blue );// get red and green from the original, and change the blue to 0
         }
      }
   
   }
   //
   public void gray(Color[][] arr)
   {
      for(int j = 0; j < arr.length; j++) // arr.length = num of rows
      {
         for(int k = 0; k < arr[0].length; k++) // arr[0].length = num columns
         {
            Color tmp = arr[j][k]; // accessing a spot and chagnign the color
            double colour = tmp.getRed() + tmp.getGreen() + tmp.getBlue();
            double yes = colour/3;
            arr[j][k] = new Color( (int)yes, (int)yes, (int)yes);// get red and green from the original, and change the blue to 0
         }
      }
   
   }
   //
   public void splash(Color[][] arr)
   {
      for(int j = 0; j < arr.length; j++) // arr.length = num of rows
      {
         for(int k = 0; k < arr[0].length; k++) // arr[0].length = num columns
         {
            Color tmp = arr[j][k]; // accessing a spot and chagnign the color
            double color = tmp.getGreen() + tmp.getBlue();
            double gray = color/2;
            
            if(tmp.getRed() > color)
            {
               arr[j][k] = new Color( tmp.getRed(), tmp.getGreen(), tmp.getBlue());
            }
            else
            {
               arr[j][k] = new Color( (int)gray, (int)gray, (int)gray);
            }
            
         }
      }
   
   }
   //
   public void blur(Color[][] arr)
   {
      for(int j = 1; j < arr.length-1; j++) 
      {
         for(int k = 1; k < arr[0].length-1; k++) 
         {
            Color tmp = arr[j][k];
            Color up = arr[j-1][k];
            Color down = arr[j+1][k];
            Color left = arr[j][k-1];
            Color right = arr[j][k+1];
            int red = (tmp.getRed() + up.getRed() + down.getRed() + left.getRed() + right.getRed())/5;
            int green = (tmp.getGreen() + up.getGreen() + down.getGreen() + left.getGreen() + right.getGreen())/5;
            int blue = (tmp.getBlue() + up.getBlue() + down.getBlue() + left.getBlue() + right.getBlue())/5;
            arr[j][k] = new Color( red, green, blue );
         }
      }
   }
  
   public void pixelate(Color[][] arr)
   {
    for(int j = 0; j < arr.length - 10; j += 10) // arr.length = num of rows
      {
         for(int k = 0; k < arr[0].length - 10; k += 10) // arr[0].length = num columns
         {
            Color tmp = arr[j][k];
            for(int y = 0; y < 10; y++)
            {
               for(int x = 0; x < 10; x++)
               {
                  arr[j+x][k+y] = tmp;
               }
            }             
         }
      }
   }
  
   public void mirrorLR(Color[][] arr)
   {
    for(int j = 0; j < arr.length; j++) // arr.length = num of rows
      {
         for(int k = 0; k < (int)arr[0].length/2; k++) // arr[0].length = num columns
         {
           Color tmp = arr[j][k];
           arr[j][arr[0].length-1-k] = tmp;  
         }
      }
   }
   
   public void mirrorUD(Color[][] arr)
   {
    for(int j = 0; j < (int)arr.length/2; j++) // arr.length = num of rows
      {
         for(int k = 0; k < arr[0].length; k++) // arr[0].length = num columns
         {
           Color tmp = arr[j][k];
           arr[arr.length-1-j][k] = tmp;  
         }
      }
   }

   public void flipUD(Color[][] arr) 
    {
        int rows = arr.length;
        int columns = arr[0].length;

        for(int i = 0; i < rows/2; i++) 
        {
            for(int j = 0; j < columns; j++) 
            {
                Color tmp = arr[i][j];
                arr[i][j] = arr[rows-1-i][j];
                arr[rows-1-i][j] = tmp;
            }
        }
    }

     public void flipLR(Color[][] arr) 
    {
        int rows = arr.length;
        int columns = arr[0].length;

        for(int i = 0; i < rows; i++) 
        {
            for(int j = 0; j < columns/2; j++) 
            {
                Color tmp = arr[i][j];
                arr[i][j] = arr[i][columns-1-j];
                arr[i][columns-1-j] = tmp;
            }
        }
    }

    public void sepia(Color[][] arr) 
    {
        int rows = arr.length;
        int columns = arr[0].length;

        for(int i = 0; i < rows; i++) 
        {
            for(int j = 0; j < columns; j++) 
            {
                int red = ((.393(arr[i][j].getRed())) + (.769(arr[i][j].getGreen())) + (.189(arr[i][j].getBlue())));
                int green = ((.349(arr[i][j].getRed())) + (.686(arr[i][j].getGreen())) + (.168(arr[i][j].getBlue())));
                int blue = ((.272(arr[i][j].getRed())) + (.534(arr[i][j].getGreen())) + (.131(arr[i][j].getBlue())));
                arr[i][j] = new Color(red,green,blue);
                           
            }
        }
    }
   // mirror LR(only loop through half of the columns; get the pixel at j,k 
   //**********arr[j][arr[0].length-1-k] (row = [j] column = [])*********




	//--------> your new methods go here   <--------------
	
	
	
  
}
//
// end of file
//