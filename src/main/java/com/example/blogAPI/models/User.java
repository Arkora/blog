package com.example.blogAPI.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class User {

    private  static final int MIN_PASSWORD_LENGTH = 8;
    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,generator = "user_sequence")
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "first_name",nullable = false,columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name",nullable = false,columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "email",columnDefinition = "TEXT",nullable = false,unique = true)
    private String email;

    @Column(name="username", columnDefinition = "TEXT", nullable = false,unique = true)
    private String username;

    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;
    @Column(name = "created_at",columnDefinition = "DATE DEFAULT CURRENT_DATE",updatable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Post> posts;


}
