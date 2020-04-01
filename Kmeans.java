/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import static datamining.ClusterParam.image;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.sqrt;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

/**
 * @author Clemens
 * @author Hakkar
 */
public class Kmeans extends ClusterParam{
    
    
    private int[][] centroidsofpixels;
    private static int k;
    private static int[][] newcentroids = new int[100][4];
    
   
     public static void kmeans() throws IOException {
         
        new Kmeans().getImage(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Bitte k eingeben");
        String s = br.readLine();
        k = Integer.parseInt(s);
      
        new Kmeans().initializecentroids();
        new Kmeans().calculate();

    }
     
     public void initializecentroids() {
        int r;
        int g;
        int b;
        for (int i = 0; i < k; i++) {
            r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            g = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            b = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            RGBpoint current = new RGBpoint();
            current.R = r;
            current.G = g;
            current.B = b;
            centroids.add(current);
        }

    }
     
     
     public void calculate() throws IOException {
        int color, red, green, blue;
        centroidsofpixels = new int[width][height];

        for (int t = 0; t < 5; t++) {      //10 Iterationen
            for (int j = 0; j < height; j++) {      //pixelwise die pixel durchgehen //Zeile
                for (int p = 0; p < width; p++) {   //Spalte
                    color = image.getRGB(p, j);
                    // Components will be in the range of 0..255:
                    blue = color & 0xff;
                    green = (color & 0xff00) >> 8;
                    red = (color & 0xff0000) >> 16;
                    double mindis = 1000000;
                    int c_nr = 0;
                    for (int c = 0; c < k; c++) {       //Distanzen von x(i) zu allen k centroiden berechnen
                        double distance = sqrt((blue - centroids.get(c).B) ^ 2 + (red - centroids.get(c).R) ^ 2 + (green - centroids.get(c).G) ^ 2);
                        if (distance < mindis) {
                            mindis = distance;
                            c_nr = c;
                        }
                    }
                    centroidsofpixels[p][j] = c_nr;
                    addtocentroids(red, green, blue, c_nr);
                }

            }
            updatecentroids();
            zerodacentroids();
        }
        createOutput();
    }
     
       public void zerodacentroids() {
        for (int z = 0; z < k; z++) {
            newcentroids[z][0] = 0;
            newcentroids[z][1] = 0;
            newcentroids[z][2] = 0;
            newcentroids[z][3] = 0;
        }
    }
       
        public void addtocentroids(int r, int g, int b, int n) {        //addtocentroids soll den rgb wert dem centroid n zurechnen zwecks neue position der centroiden
        newcentroids[n][0] += r;
        newcentroids[n][1] += g;
        newcentroids[n][2] += b;
        newcentroids[n][3]++;
    }
        
         public void updatecentroids() throws IOException {
        for (int z = 0; z < k; z++) {
            System.out.print(newcentroids[z][0] + " " + newcentroids[z][1] + " " + newcentroids[z][2] + " " + newcentroids[z][3] + " && ");
            newcentroids[z][0] = newcentroids[z][0] / newcentroids[z][3];
            newcentroids[z][1] = newcentroids[z][1] / newcentroids[z][3];
            newcentroids[z][2] = newcentroids[z][2] / newcentroids[z][3];
            centroids.get(z).R = newcentroids[z][0];
            centroids.get(z).G = newcentroids[z][1];
            centroids.get(z).B = newcentroids[z][2];
            System.out.println(centroids.get(z).R + " " + centroids.get(z).G + " " + centroids.get(z).B);
        }

    }
         
            public void createOutput() throws IOException {
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int n = centroidsofpixels[w][h];
                int rgb = new Color(centroids.get(n).R, centroids.get(n).G, centroids.get(n).B).getRGB();
                image.setRGB(w, h, rgb);
            }
        }
       save();
        

    }
            
}