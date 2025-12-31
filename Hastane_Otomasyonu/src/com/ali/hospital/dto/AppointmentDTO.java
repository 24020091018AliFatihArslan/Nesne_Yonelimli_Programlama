package com.ali.hospital.dto;

public class AppointmentDTO {
    public long id;
    public String patientName;
    public String doctorName;
    public String clinicName;
    public String dateTime;

    public AppointmentDTO(long id, String patientName, String doctorName, String clinicName, String dateTime) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.clinicName = clinicName;
        this.dateTime = dateTime;
    }
}
