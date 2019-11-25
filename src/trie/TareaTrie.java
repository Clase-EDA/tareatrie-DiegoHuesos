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
        
        //System.out.println("CantPalabras\t Merge\t\t Trie");
        int num = 10000;
        
        for(int i =0;i<10; i++){
            try(Scanner sc = new Scanner(new File("words.txt"));){
                
                //LEER Y AGREGAR LAS PALARAS A UNA LISTA
                ArrayList<String> list = new ArrayList<>();
                int cont = 0;
                while(cont < num){
                    list.add(sc.next());
                    cont++;
                }
                //System.out.print(cont + "\t\t");
                
                //PASAR LA LISTA A UN ARREGLO
                String [] arr = new String[cont];
                for(int j = 0; j<cont; j++){
                    arr[j] = list.get(j);
                }

                //¿TimeUnit.SECONDS.sleep(1);?
                long startTime, endTime; 
                    
                //MERGE_SORT:
                startTime = System.currentTimeMillis();
                MergeExperiments me = new MergeExperiments();
                me.mergeSort(arr);
                endTime = System.currentTimeMillis();
                System.out.print((endTime-startTime) +"\t");

                //TRIE_SORT:
                startTime = System.currentTimeMillis();
                WordTrie.ordenaLexicograficamente(list);
                endTime = System.currentTimeMillis();
                System.out.println((endTime-startTime));
                
                //INCREMENTAR LA CANTIDAD DE PALABRAS:
                num += 2000;
            }        
            catch(Exception e){
                System.out.println("Error: " + e.toString());
            }
        }
    }
}