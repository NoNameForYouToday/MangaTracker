package com.NMFY.MangaTracker.Database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public String name;
    public String language;
    public String outDir;
    public String mode;
    public Long numberOfMangas;

    public Author(String name, String language, String outDir, String mode) {
        this.name = name;
        this.language = language;
        this.outDir = outDir;
        this.mode = mode;
    }

    public Author() {

    }
}
