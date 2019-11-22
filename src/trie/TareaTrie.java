/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TareaTrie {
    
    public static void main(String[] args) throws FileNotFoundException, InterruptedException{
        
        try(Scanner sc = new Scanner(new File("words.txt"));){
            
            ArrayList<String> list = new ArrayList<>();
            int cont = 0;
            while(sc.hasNext()){
                list.add(sc.next());
                cont++;
            }
            System.out.println("Cantidad de palabras: " + cont);
            String [] arr = new String[cont];
            for(int i = 0; i<cont; i++){
                arr[i] = list.get(i);
            }
            
            
            long startTime, endTime; 
            startTime = System.currentTimeMillis();
            //MergeSort.mergeSort(arr, 0, cont);
            
            MergeExperiments me = new MergeExperiments();
            me.mergeSort(arr);
            //TimeUnit.SECONDS.sleep(1);
            
            endTime = System.currentTimeMillis();
            System.out.println("Merge: "+( (endTime-startTime) )+" milisegundos");
            
            startTime = System.currentTimeMillis();
            WordTrie.ordenaLexicograficamente(list);
            //TimeUnit.SECONDS.sleep(1);
            endTime = System.currentTimeMillis();
            System.out.println("Trie: "+(endTime-startTime) +" milisegundos");
            
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
        
    }
}