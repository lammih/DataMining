/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import static datamining.Kmeans.kmeans;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Clemens
 * @author Hakkar
 */
public class Main {
    

    
   
 public static void main(String[] args) throws IOException{
     
  
    System.out.println("Möchten Sie Kmeans oder DBSCAN benutzen?");
    System.out.println("Geben Sie für Kmeans 1 und für DBSCAN 2 ein: ");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String eingabe = br.readLine();
    int temp;
    temp = Integer.parseInt(eingabe);
    String ausgabe;
    switch(temp){
        case 1: kmeans();
        break; 
        //case 2: dbscan(); -> noch nicht implementiert
        //System.out.println("Sie haben DBSCAN ausgewählt");
        //System.out.println("Bild wird bearbeitet");
        //break;
        default: ausgabe = "Noch nicht implementiert"; // kann nachdem "dbscan();" implementiert wurde gelöcht werden!
        break;
 
 }
 

    

}
    
    
}
