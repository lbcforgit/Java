/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//BINARY SEARCH - PARALLEL
package edu.luisbc.binarysearch.parallel;

import java.util.List;

/**
 *
 * @author luisbc
 */
public class SequentialSearchS {
    //ATRIBUTES
    private List<Integer> data;
    //CONSTRUCTOR
    SequentialSearchS(List<Integer> data) {
        this.data = data;
    }
    //METHOD
    public int search(int target){
        for (Integer data1 : data) {
            if (data1 == target)
                return data.indexOf(target);
        }
        //Not found
        return -1;
    }    
}
