package com.example.blogAPI.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;
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
    @Column(name = "firstname",nullable = false,columnDefinition = "TEXT")
    private String firstname;
    @Column(name = "lastname",nullable = false,columnDefinition = "TEXT")
    private String lastname;
    @Email
    @Column(name = "email",columnDefinition = "TEXT",nullable = false,unique = true)
    private String email;

    @Column(name="username", columnDefinition = "TEXT", nullable = false,unique = true)
    private String username;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
   // @Column(name = "photo",columnDefinition = "BYTEA")
    private byte[] photo;

    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    @JsonIgnore
    private String password;
    @Column(name = "created_at",columnDefinition = "DATE DEFAULT CURRENT_DATE",updatable = false)
    private Date createdAt;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user" )
    @JsonManagedReference
    @OrderBy("createdAt desc")
    private Collection<Post> posts;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Collection<Notification> notifications;




    public User(String firstname,String lastname,String email,String username,String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String firstname, String lastname, String email, String username, byte[] photo, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.photo = photo;
        this.password = password;
    }
}
