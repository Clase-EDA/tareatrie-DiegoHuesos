/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

/**
 *
 * @author huesos
 */
public class MergeExperiments <T extends Comparable<T> > {
    //Merge: O(m+n)
    public void mergeIndices(T[]arr, int low, int high){

        T [] aux = (T[]) new Comparable[high+1 - low];

        for(int c=low; c<high+1; c++)
          aux[c-low] = arr[c];

        int i=0;
        int mid= (int) Math.floor(aux.length/2);
        int j=mid;

        while( i<mid && j<aux.length){
          if( aux[i].compareTo(aux[j]) < 0)
            arr[low++] = aux[i++];
          else
            arr[low++] = aux[j++];
        }

        while( i<mid )
          arr[low++] = aux[i++];

        while(j<aux.length)
          arr[low++] = aux[j++];
    }
    
    //O(nlog(n))
    public void mergeSort(T [] arr){
        mergeSort(arr, 0, arr.length-1);
    }
    private void mergeSort(T[] arr, int low, int high){
        if(low < high){
            int mid = (low+high)/2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            merge(arr, low, high);
        }
    }
    
    
    public void merge(T[]arr, int low, int high){
        if(arr == null | high <= low | arr.length<=1 )
            return;
        
        T [] aux = (T[]) new Comparable[high+1 - low];

        for(int c=low; c<high+1; c++)
          aux[c-low] = arr[c];

        int i=0;
        int mid= (int) Math.floor(aux.length/2);
        int j=mid;

        while( i<mid && j<aux.length){
          if( aux[i].compareTo(aux[j]) <= 0)
            arr[low++] = aux[i++];
          else
            arr[low++] = aux[j++];
        }

        while( i<mid )
          arr[low++] = aux[i++];

        while(j<aux.length)
          arr[low++] = aux[j++];
    }
    
    //ARRAY_TO_STRING METHOD:
    public String arrayToString(T [] a){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<a.length; i++)
            sb.append( a[i].toString() + " ");
        return sb.toString();
    }
    
    //MAIN:
    public static void main (String [] args){
        System.out.println("Expeaaaa");
        MergeExperiments me = new MergeExperiments();
        Integer [] arr = {8, 4 ,30, 1, 13, 2};
        System.out.println("Original Array: " + me.arrayToString(arr));
        
        me.mergeSort(arr);
        
        System.out.println("MergeSorted Array: " + me.arrayToString(arr));
    }
}
