/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * @author Clemens
 * @author Hakkar
 */
public class ClusterParam {
    
    
     static ArrayList<Kmeans.RGBpoint> centroids = new ArrayList<Kmeans.RGBpoint>();
     static int height; //Höhe des Bildes
     static int width; //Breite des Bildes
     static BufferedImage image;

    
    
       class RGBpoint {

        int R;
        int G;
        int B;
    }
       
      
       public void getImage() throws IOException{
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Bitte Pfad zu .jpg angeben:");
        String path = br.readLine();
        File file = new File(path);
        image = ImageIO.read(file);
        height = image.getHeight();
        width = image.getWidth();
        System.out.println("Dimensions: " + width + " x " + height);
        //c:\Users\Hakkar\Desktop\test.jpg
       
       }
       
       
        public static void save() throws IOException {
           
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Bitte geben Sie den Pfad an in dem das Bild abgespeichert werden soll");
        String eingabe = br.readLine();
        ImageIO.write(image, "jpg", new File(eingabe));
        }
        
    
}
