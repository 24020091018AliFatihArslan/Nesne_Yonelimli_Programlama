package com.ali.hospital.dto;

public class ClinicDTO {
    public long id;
    public String name;

    public ClinicDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public String toString() { return name; }
}
