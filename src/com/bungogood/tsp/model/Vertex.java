package com.bungogood.tsp.model;

public class Vertex {
    public final String id, name;
    public final double lat, lng;

    public Vertex (String id, String name, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public static int distance(Vertex a, Vertex b) {
        int Radius = 6371; //km
        double dLat = Math.toRadians(a.lat-b.lat);
        double dLon = Math.toRadians(a.lng-b.lng);
        double working;
        working = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(b.lat)) * Math.cos(Math.toRadians(a.lat)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        working = Radius * 2 * Math.atan2(Math.sqrt(working), Math.sqrt(1-working));
        return (int) working;
    }
}
