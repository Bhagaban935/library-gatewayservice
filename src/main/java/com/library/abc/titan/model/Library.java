package com.library.abc.titan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity(name = "library")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookName", nullable = false)
    private String bookName;

    @Column(name = "bookDescription", nullable = false)
    private String bookDescription;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "bookGenre", nullable = false)
    private String bookGenre;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}
