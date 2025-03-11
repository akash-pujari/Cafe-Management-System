package com.inn.cafe.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {
    Integer id;
    String name;
    String email;
    String contactNumber;
    String role;

    public UserWrapper(Integer id, String name, String email, String contactNumber, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.role = role;
    }

}
