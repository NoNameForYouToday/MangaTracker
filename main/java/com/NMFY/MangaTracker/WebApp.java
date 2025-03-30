package com.NMFY.MangaTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebApp {

    @Autowired
    private MangaI mangaDB;

    @GetMapping("/")
    public String defualtPage(){
        return "index";
    }
    @GetMapping("/addManga")
    public String addMangaPage(){
        return "pages/addManga";
    }
    @PostMapping("/submitManga")
    public String addManga(@ModelAttribute("manga") Manga manga){
        mangaDB.save(manga);
        return "redirect:/";
    }
    @GetMapping("/api/delete")
    public String deleteManga(@RequestParam(required = true) String mangaID){
        mangaDB.deleteByMangaID(mangaID);
        return "redirect:/";
    }
}
