package com.NMFY.MangaTracker;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mangas")
public class Manga {

    @Id
    private long id;
    private String mangaID;
    private String title;
    private String language;
    private float highestVolume, highestChapter;
    private String outDir;
    private String state;


    public Manga( String mangaID, String title, String language, float highestVolume, float highestChapter, String outDir, String state) {

        this.mangaID = mangaID;
        this.title = title;
        this.language = language;
        this.highestVolume = highestVolume;
        this.highestChapter = highestChapter;
        this.outDir = outDir;
        this.state = state;
    }

    public Manga(String mangaID, String title, String language, String state, String outDir) {

        this.mangaID = mangaID;
        this.title = title;
        this.language = language;
        this.state = state;
        this.outDir = outDir;
        this.highestVolume = 0.0f;
        this.highestChapter = 0.0f;
    }
    public Manga( String mangaID, String title, String language,  String outDir) {
        this.mangaID = mangaID;
        this.title = title;
        this.language = language;
        this.state = "initialised";
        this.outDir = outDir;
        this.highestVolume = 0.0f;
        this.highestChapter = 0.0f;
    }
    public Manga(String mangaID,  String outDir, String language) {
        this.mangaID = mangaID;
        this.language = language;
        this.state = "initialised";
        this.outDir = outDir;
        this.highestVolume = 0.0f;
        this.highestChapter = 0.0f;
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
