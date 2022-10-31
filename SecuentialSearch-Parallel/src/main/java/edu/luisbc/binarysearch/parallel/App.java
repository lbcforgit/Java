/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

//PROGRAM THAT COMPARES THE PERFORMANCE OF PARALLELISM USING SEQUENTIAL SEARCH
package edu.luisbc.binarysearch.parallel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author luisbc
 */
public class App {

    public static void main(String[] args) {
        //Variables for PARALLELISM        
        int threshold = 1000;
        int pLevel = 8;
        //VARIABLES FOR THE PROBLEM
        List<Integer> data = new ArrayList<>();
        int size = 10000;
        int range = 900;
        int target = 623;
        int pos;
        //Variables for calculating time
        long start, end;
        //CREATE RANDOM DATA
        for (int i = 0; i < size; i++) {
            data.add(new Random().nextInt(range));
        }
        //Sort data
        //Collections.sort(data);
        //Remove duplicates
        List<Integer> dataNoDuplicates = 
                        new ArrayList<>(new LinkedHashSet<>(data));
        //Print a small chunk of data
        System.out.println("------ ORIGINAL DATA  (a chunk of)  -------");
        for (int i = 0; i<20; i++){
            System.out.printf("%8d\n\n", dataNoDuplicates.get(i));
        }
        //Secuential search
        start = System.nanoTime();
        SequentialSearchS search1 = new SequentialSearchS(dataNoDuplicates);
        pos = search1.search(target);
        end = System.nanoTime();
        System.out.println("------- SEQUENTIAL SEARCH -------");
        System.out.println("Target found at position:\t" + pos);
        System.out.printf("\tTime used: %f miliseconds\n\n", 
                                (end-start)/Math.pow(10, 6));
        
        //Parallel search
        ForkJoinPool fjp = new ForkJoinPool(pLevel);
        start = System.nanoTime();
        SequentialSearchP search2 = new SequentialSearchP(dataNoDuplicates,
                                                           threshold, 
                                                            target);
        pos = (int) fjp.invoke(search2);
        end = System.nanoTime();
        System.out.println("------- PARALLEL SEQUENTIAL SEARCH -------");
        System.out.println("Target found at position:\t" + pos);
        System.out.printf("\tTime used: %f miliseconds\n\n", 
                                (end-start)/Math.pow(10, 6));
        
        //Binary search
        start = System.nanoTime();
        Collections.sort(dataNoDuplicates);
        BinarySearch search3 = new BinarySearch(dataNoDuplicates);
        pos = search3.search(target);
        end = System.nanoTime();
        System.out.println("------- BINARY SEARCH -------");
        System.out.println("Target found at position:\t" + pos);
        System.out.printf("\tTime used: %f miliseconds\n\n", 
                                (end-start)/Math.pow(10, 6));
        
    }
}
