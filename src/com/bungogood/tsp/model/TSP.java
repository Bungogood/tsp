package com.bungogood.tsp.model;

/**
 * Traveling Salesman Problem is a undirected graph
 * where every vertex connects to every other vertex
 */

public class TSP extends Graph {

    public TSP () {
        super();
    }

    /**
     * @param vertex A vertex to be added to the TSP
     * @return If the addition is successful
     *
     * Adds a vertex to the graph using the super class
     * Then connects to all other vertices
     */
    @Override
    public boolean add(Vertex vertex) {
        if (!super.add(vertex)) return false;

        for (Vertex other : vertices) {
            if (vertex != other) {
                int distance = Vertex.distance(vertex, other);
                add(new Edge(vertex, other, distance));
                add(new Edge(other, vertex, distance));
            }
        }

        return true;
    }
}
