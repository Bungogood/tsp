package com.bungogood.tsp;

import com.bungogood.tsp.model.*;
import com.bungogood.tsp.algo.*;

public class Main {
    public static void main(String[] args) {
        TSP tsp = IO.readTSP("data/continental.txt");
        int lowerBound = OneTree.lowerBound(tsp);
        System.out.println("lower bound: " + lowerBound);
    }
}
