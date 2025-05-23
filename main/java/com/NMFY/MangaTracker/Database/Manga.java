package com.NMFY.MangaTracker.Database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mangas")
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String mangaID;
    private String title;
    private String language;
    private float highestVolume, highestChapter;
    private String outDir;
    private String state;
    private String mode;

    public Manga( String mangaID, String title, String language, float highestVolume, float highestChapter, String outDir, String state) {

        this.mangaID = mangaID;
        this.title = title;
        this.language = language;
        this.highestVolume = highestVolume;
        this.highestChapter = highestChapter;
        this.outDir = outDir;
        this.state = state;
    }

    public Manga(String mangaID, String title, String language, String state,String mode, String outDir) {

        this.mangaID = mangaID;
        this.title = title;
        this.language = language;
        this.state = state;
        this.outDir = outDir;
        this.highestVolume = 0.0f;
        this.highestChapter = 0.0f;
        this.mode = mode;
    }
    public Manga( String mangaID, String title, String language, String mode ,String outDir) {
        this.mangaID = mangaID;
        this.title = title;
        this.language = language;
        this.state = "initialised";
        this.outDir = outDir;
        this.highestVolume = 0.0f;
        this.highestChapter = 0.0f;
        this.mode = mode;
    }
    public Manga(String mangaID,  String outDir,String mode, String language) {
        this.mangaID = mangaID;
        this.language = language;
        this.state = "initialised";
        this.outDir = outDir;
        this.highestVolume = 0.0f;
        this.highestChapter = 0.0f;
        this.mode = mode;
    }
    public Manga(String mangaID,  String outDir,String mode) {
        this.mangaID = mangaID;
        this.language = "en";
        this.state = "initialised";
        this.outDir = outDir;
        this.highestVolume = 0.0f;
        this.highestChapter = 0.0f;
        this.mode = mode;
    }
    public Manga(String mangaID,  String outDir) {
        this.mangaID = mangaID;
        this.language = "en";
        this.state = "initialised";
        this.outDir = outDir;
        this.highestVolume = 0.0f;
        this.highestChapter = 0.0f;
    }
    public Manga() {

    }
}
