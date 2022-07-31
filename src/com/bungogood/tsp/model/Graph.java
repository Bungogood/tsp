package com.bungogood.tsp.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

    protected final Set<Vertex> vertices;
    protected final Set<Edge> edges;
    private final Map<Vertex, Map<Vertex, Edge>> outgoing;
    private final Map<Vertex, Map<Vertex, Edge>> incoming;

    public Graph () {
        this.vertices = new HashSet<Vertex>();
        this.edges = new HashSet<Edge>();
        this.outgoing = new HashMap<Vertex, Map<Vertex, Edge>>();
        this.incoming = new HashMap<Vertex, Map<Vertex, Edge>>();
    }

    public Graph (Graph graph) {
        this.vertices = graph.getVertices();
        this.edges = graph.getEdges();
        this.outgoing = graph.getOutgoing();
        this.incoming = graph.getIncoming();
    }

    public boolean add(Vertex vertex) {
        if (!vertices.add(vertex)) return false;
        outgoing.put(vertex, new HashMap<Vertex, Edge>());
        incoming.put(vertex, new HashMap<Vertex, Edge>());
        return true;
    }

    public boolean add(Edge edge) {
        if (!edges.add(edge)) return false;
        add(edge.from);
        add(edge.to);
        outgoing.get(edge.from).put(edge.to, edge);
        incoming.get(edge.to).put(edge.from, edge);
        return true;
    }

    public boolean remove(Vertex vertex) {
        if (!vertices.remove(vertex)) return false;

        for (Edge edge : outgoing.get(vertex).values()) {
            incoming.get(edge.to).remove(edge.from);
        }

        for (Edge edge : incoming.get(vertex).values()) {
            outgoing.get(edge.from).remove(edge.to);
        }

        outgoing.remove(vertex);
        incoming.remove(vertex);

        return true;
    }

    public boolean remove(Edge edge) {
        if (!edges.remove(edge)) return false;

        incoming.get(edge.to).remove(edge.from);
        outgoing.get(edge.from).remove(edge.to);

        return true;
    }

    public int order() {
        return vertices.size();
    }

    public int size() {
        return edges.size();
    }

    public int degree(Vertex vertex) {
        return outgoing.get(vertex).size();
    }

    public boolean contains(Vertex vertex) {
        return vertices.contains(vertex);
    }

    public boolean contains(Edge edge) {
        return getEdges().contains(edge);
    }

    public Vertex getVertex() {
        return vertices.iterator().next();
    }

    public Set<Vertex> getVertices() {
        return new HashSet<Vertex>(vertices);
    }

    public Edge getEdge() {
        return edges.iterator().next();
    }

    public Edge getEdge(Vertex u, Vertex v) {
        return outgoing.get(u).get(v);
    }

    public Set<Edge> getEdges() {
        return new HashSet<Edge>(edges);
    }

    public Set<Edge> getEdges(Vertex vertex) {
        return new HashSet<Edge>(outgoing.get(vertex).values());
    }

    public Map<Vertex, Map<Vertex, Edge>> getOutgoing() {
        Map<Vertex, Map<Vertex, Edge>> out = new HashMap<Vertex, Map<Vertex, Edge>>();
        for (Vertex vertex : vertices) {
            out.put(vertex, new HashMap<Vertex, Edge>(outgoing.get(vertex)));
        }
        return out;
    }

    public Map<Vertex, Map<Vertex, Edge>> getIncoming() {
        Map<Vertex, Map<Vertex, Edge>> in = new HashMap<Vertex, Map<Vertex, Edge>>();
        for (Vertex vertex : vertices) {
            in.put(vertex, new HashMap<Vertex, Edge>(incoming.get(vertex)));
        }
        return in;
    }

    public int dist(Vertex u, Vertex v) {
        return outgoing.get(u).get(v).distance;
    }

    public int cost() {
        int total = 0;
        for (Edge edge : edges) total += edge.distance;
        return total;
    }
}
