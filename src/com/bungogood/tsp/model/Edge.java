package com.bungogood.tsp.model;

public class Edge implements Comparable<Edge> {
    public final Vertex from;
    public final Vertex to;
    public final int distance;

    public Edge(Vertex from, Vertex to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.distance - edge.distance;
    }

    @Override
    public String toString() {
        return from.id + "->" + to.id + ":" + distance;
    }
}
