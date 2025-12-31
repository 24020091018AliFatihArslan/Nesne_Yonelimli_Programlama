package com.ali.hospital.domain;

public class Doctor extends BaseUser {
    private final long clinicId;

    public Doctor(long id, String username, String password, String fullName, long clinicId) {
        super(id, username, password, fullName, Role.DOCTOR);
        this.clinicId = clinicId;
    }

    public long getClinicId() { return clinicId; }
}
