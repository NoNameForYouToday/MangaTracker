package com.NMFY.MangaTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DatabaseHandler {

    @Autowired
    private MangaI mangaDB;

    @GetMapping("/mangas")
    public List<Manga> getMangas(){
        return mangaDB.findAll();
    }


}
