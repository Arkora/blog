package com.example.blogAPI.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "Notification")
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_sequence",sequenceName = "notification_sequence",allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,generator = "notification_sequence")
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "body",nullable = false,columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "id",nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "postId",nullable = false)
    private Long postId;

    public Notification(String body, User user,Long postId) {
        this.body = body;
        this.user = user;
        this.postId = postId;
    }
}
