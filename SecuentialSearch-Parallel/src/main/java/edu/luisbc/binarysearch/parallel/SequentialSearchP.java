/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.luisbc.binarysearch.parallel;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author luisbc
 */
public class SequentialSearchP extends RecursiveTask<Integer>{
    //Parallelism
    int threshold;
    int start, end;
    //ATRIBUTES
    private List<Integer> data;
    private int target;    
    //CONSTRUCTOR to use in the main class
    SequentialSearchP(List<Integer> data, int threshold, int target) {
        this.data = data;
        this.threshold = threshold;
        this.start = 0;
        this.end = data.size();
        this.target = target;
    }        
    //CONSTRUCTOR to use in compute method
    SequentialSearchP(List<Integer> data, int threshold, int target, 
                                            int start, int end) {
        this.data = data;
        this.threshold = threshold;
        this.start = start;
        this.end = end;
        this.target = target;
    }   
    
    //METHOD FOR PARALLELIZATION
    @Override
    protected Integer compute() {
        //variables
        int center;
        int result;
        
        //Condition for sequential operation
        if ( (end - start) < threshold ) {
            //Search
            for (int i = start; i < end; i++) {
                if (data.get(i) == target)
                    return i;
            }
                
        } else {
        //Parallelization of the task
        center = (end + start)/2;      
        
        SequentialSearchP task1 = 
              new SequentialSearchP(data, threshold, target, start, center);
        SequentialSearchP task2 = 
              new SequentialSearchP(data, threshold, target, center, end);
        task1.fork();
        task2.fork();
        
        result = task1.join();
        if (result != -1 ) 
            return result;
        result = task2.join();
        if (result != -1)
            return result;
       
        }
         //Not found
        return -1;
    }    
}
