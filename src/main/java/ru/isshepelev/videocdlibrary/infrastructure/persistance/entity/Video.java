package ru.isshepelev.videocdlibrary.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

}
