package com.bungogood.tsp.algo;

import com.bungogood.tsp.model.*;
import java.util.*;

public class Greedy {
    public static int upperBound(Graph graph) {
        return Greedy.greedy(graph).cost();
    }

    public static Tour greedy(Graph graph) {

        Tour shortestTour = null;

        for (Vertex vertex : graph.getVertices()) {
            Tour newTour = Greedy.greedy(graph, vertex);
            if (shortestTour == null || newTour.cost() < shortestTour.cost()) {
                shortestTour = newTour;
            }
        }

        return shortestTour;
    }

    public static Tour greedy(Graph graph, Vertex vertex) {
        List<Vertex> vertices = new ArrayList<>();
        Graph remaining = new Graph(graph);

        vertices.add(vertex);

        while (remaining.order() > 1) {
            Edge shortest = null;
            for (Edge edge : remaining.getEdges(vertex)) {
                if (shortest == null || edge.distance() < shortest.distance()) {
                    shortest = edge;
                }
            }
            remaining.remove(vertex);
            vertex = shortest.to();
            vertices.add(vertex);
        }

        return new Tour(graph, vertices);
    }
}
