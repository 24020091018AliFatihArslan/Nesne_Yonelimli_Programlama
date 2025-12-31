package com.ali.hospital.domain;

public abstract class BaseUser {
    protected final long id;
    protected final String username;
    protected final String password;
    protected final String fullName;
    protected final Role role;

    protected BaseUser(long id, String username, String password, String fullName, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public Role getRole() { return role; }
}
