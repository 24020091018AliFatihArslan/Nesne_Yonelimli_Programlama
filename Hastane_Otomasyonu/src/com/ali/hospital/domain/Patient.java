package com.ali.hospital.domain;

public class Patient extends BaseUser {
    private final String tcNo;

    public Patient(long id, String username, String password, String fullName, String tcNo) {
        super(id, username, password, fullName, Role.PATIENT);
        this.tcNo = tcNo;
    }

    public String getTcNo() { return tcNo; }
}
