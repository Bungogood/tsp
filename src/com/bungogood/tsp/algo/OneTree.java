package com.bungogood.tsp.algo;

import com.bungogood.tsp.model.*;

/**
 * A Minimum Spanning Tree with 1 vertex removed
 * Then adding 2 shortest edges from the vertex to the MST
 *
 * OneTree Cost <= TSP Cost
 */

public class OneTree {
    public static int lowerBound(Graph graph) {
        return OneTree.oneTree(graph).cost();
    }

    public static Graph oneTree(Graph graph) {
        int lowerBound = Integer.MIN_VALUE;
        Graph oneTree = null;

        for (Vertex vertex : graph.getVertices()) {
            Graph newTree = OneTree.oneTree(graph, vertex);
            if (newTree.cost() > lowerBound) {
                lowerBound = newTree.cost();
                oneTree = newTree;
            }
        }

        return oneTree;
    }

    public static Graph oneTree(Graph graph, Vertex vertex) {
        assert graph.degree(vertex) >= 2;

        Graph shrunk = new Graph(graph);
        shrunk.remove(vertex);

        MST mst = Prim.solve(shrunk);
        assert mst != null;
        Graph oneTree = new Graph(mst);

        Edge small = null;
        Edge smallest = null;

        for (Edge edge : graph.getEdges(vertex)) {
            if (smallest == null || edge.distance() < smallest.distance()) {
                small = smallest;
                smallest = edge;
            } else if (small == null || edge.distance() < small.distance()) {
                small = edge;
            }
        }

        oneTree.add(small);
        oneTree.add(smallest);

        return oneTree;
    }
}
