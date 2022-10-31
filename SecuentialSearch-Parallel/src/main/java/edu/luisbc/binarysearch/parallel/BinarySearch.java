/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//BINARY SEARCH - SEQUENTIAL
package edu.luisbc.binarysearch.parallel;

import java.util.List;

/**
 *
 * @author luisbc
 */
public class BinarySearch {
    //ATRIBUTES
    private List<Integer> data;   
    //CONSTRUCTOR
    BinarySearch(List data){
        this.data = data;        
    }
    //METHODS
    public int search(int target){
        //Variables for the search
        int center, iLimit = 0, uLimit = (data.size() - 1) ; 
        //Algorithm
        while(iLimit <= uLimit) {
            center = ((uLimit - iLimit)/2 + iLimit);
            if (data.get(center) == target)
                return center;
            else if (target < data.get(center))
                uLimit = center - 1;
            else
                iLimit = center + 1;
        }
        //If the target isn't found
        return -1;       
    }    
}
