package com.example.blogAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

//    private User user;
//    private Post post;


}
