package com.ali.hospital.domain;

public class Clinic {
    private final long id;
    private final String name;

    public Clinic(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() { return id; }
    public String getName() { return name; }

    @Override public String toString() { return name; }
}
