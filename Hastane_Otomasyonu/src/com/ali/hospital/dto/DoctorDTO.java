package com.ali.hospital.dto;

public class DoctorDTO {
    public long id;
    public String fullName;
    public long clinicId;
    public String clinicName;

    public DoctorDTO(long id, String fullName, long clinicId, String clinicName) {
        this.id = id;
        this.fullName = fullName;
        this.clinicId = clinicId;
        this.clinicName = clinicName;
    }

    @Override public String toString() { return fullName; }
}
