package com.example.blogAPI.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;


import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @SequenceGenerator(name = "post_sequence",sequenceName = "post_sequence",allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,generator = "post_sequence")
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name="title",columnDefinition = "TEXT")
    @NotEmpty(message = "Title needed")
    private String title;
    @Column(name="body",columnDefinition = "TEXT")
    @NotEmpty(message = "Body of post needed")
    private String body;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "post")
    private Collection<Comment> comments;


    public Post(String title, String body, Date createdAt, User user) {
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.user = user;
    }
}
