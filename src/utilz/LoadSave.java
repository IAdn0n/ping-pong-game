
package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class LoadSave {
    
    //method that stores an image into a variable
    public static BufferedImage GetAtlas(String fileName) {
        BufferedImage img = null;
        //loading specific image into a stream
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        
        if (is == null)
            return img;
        
        //load the image into img variable
        try {
            img = ImageIO.read(is);  
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        
        return img;
    }
}
