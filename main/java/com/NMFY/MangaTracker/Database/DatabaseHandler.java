package com.NMFY.MangaTracker.Database;

import com.NMFY.MangaTracker.MangaDownloaderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DatabaseHandler {
    @Autowired
    private MangaDownloaderInterface mangaDL;
    @Autowired
    private MangaInterface mangaDB;

    @GetMapping("/mangas")
    public List<Manga> getMangas(){
        return mangaDB.findAll();
    }
    @PostMapping("/recheck")
    public String checkForUpdates(){
        mangaDL.checkDatabase();
        return "Success";
    }

}
