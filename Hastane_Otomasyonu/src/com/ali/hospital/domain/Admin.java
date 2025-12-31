package com.ali.hospital.domain;

public class Admin extends BaseUser {
    public Admin(long id, String username, String password, String fullName) {
        super(id, username, password, fullName, Role.ADMIN);
    }
}
