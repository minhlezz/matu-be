package com.workspace.shopping.role;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
    private String name;

}
