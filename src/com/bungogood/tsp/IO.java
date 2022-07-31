package com.bungogood.tsp;

import com.bungogood.tsp.model.TSP;
import com.bungogood.tsp.model.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IO {

    public static TSP readTSP(String filename) {
        TSP graph = new TSP();

        double lat, lng;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                lat = Double.parseDouble(values[1]);
                lng = Double.parseDouble(values[2]);
                graph.add(new Vertex(values[0], values[3], lat, lng));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }
}
