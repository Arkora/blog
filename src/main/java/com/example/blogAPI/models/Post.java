package com.example.blogAPI.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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
    private String title;
    @Column(name="body",columnDefinition = "TEXT")
    private String body;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private User user;




}
