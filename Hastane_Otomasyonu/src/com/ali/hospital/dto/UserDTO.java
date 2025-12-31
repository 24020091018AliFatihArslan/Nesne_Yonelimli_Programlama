package com.ali.hospital.dto;

import com.ali.hospital.domain.Role;

public class UserDTO {
    public long id;
    public String username;
    public String fullName;
    public Role role;

    public UserDTO(long id, String username, String fullName, Role role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }
}
