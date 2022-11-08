package com.example.blogAPI.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
@Entity
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_sequence",sequenceName = "comment_sequence",allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,generator = "comment_sequence")
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name="body",columnDefinition = "TEXT")
    private String body;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id",referencedColumnName = "id",nullable = false)
    @JsonBackReference
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    @JsonBackReference
    private User user;

    public Comment(String body, Date createdAt, Post post, User user) {
        this.body = body;
        this.createdAt = createdAt;
        this.post = post;
        this.user = user;
    }
}
