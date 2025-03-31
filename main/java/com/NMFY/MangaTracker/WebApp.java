package com.NMFY.MangaTracker;

import com.NMFY.MangaTracker.Database.Manga;
import com.NMFY.MangaTracker.Database.MangaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebApp {

    @Autowired
    private MangaInterface mangaDB;

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


        if(manga.getLanguage()==null|| manga.getLanguage()==""){
            manga.setLanguage("en");
        }

        mangaDB.save(manga);
        return "redirect:/";
    }
    @GetMapping("/api/delete")
    public String deleteManga(@RequestParam(required = true) String mangaID){
        mangaDB.deleteByMangaID(mangaID);
        return "redirect:/";
    }
}
