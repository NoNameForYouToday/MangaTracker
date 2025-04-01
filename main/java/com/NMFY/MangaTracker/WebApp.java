package com.NMFY.MangaTracker;

import com.NMFY.MangaTracker.Database.Author;
import com.NMFY.MangaTracker.Database.AuthorInterface;
import com.NMFY.MangaTracker.Database.Manga;
import com.NMFY.MangaTracker.Database.MangaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
public class WebApp {

    @Autowired
    private MangaInterface mangaDB;

    @Autowired
    private AuthorInterface authorDB;

    @GetMapping("/")
    public String defualtPage(){
        return "index";
    }

    @GetMapping("/addManga")
    public String addMangaPage(){
        return "pages/addManga";
    }

    @GetMapping("/addAuthor")
    public String addAuthor(){
        return "pages/addAuthor";
    }

    @PostMapping("/submitManga")
    public ResponseEntity<String> addManga(@ModelAttribute("manga") Manga manga){


        if(manga.getLanguage()==null|| manga.getLanguage()==""){
            manga.setLanguage("en");
        }
        if (manga.getMode() == null || manga.getMode() == ""){
            manga.setMode("volume");
        }

        for (Manga manga1: mangaDB.findAll()){
            if (manga1.getMangaID().equals(manga.getMangaID())) return new ResponseEntity<>("Error: MangaID already exits in the database", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        mangaDB.save(manga);
        return new ResponseEntity<>(headers,HttpStatus.SEE_OTHER);
    }
    @PostMapping("/submitAuthor")
    public ResponseEntity<String> addAuthor(@ModelAttribute("author") Author author){


        if(author.getLanguage()==null|| author.getLanguage()==""){
            author.setLanguage("en");
        }
        if (author.getMode() == null || author.getMode() == ""){
            author.setMode("volume");
        }

       if (authorDB.exitsByName(author.getName())) return new ResponseEntity<>("Error: Author already exits in the database", HttpStatus.INTERNAL_SERVER_ERROR);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        authorDB.save(author);
        return new ResponseEntity<>(headers,HttpStatus.SEE_OTHER);
    }



    @GetMapping("/api/delete")
    public String deleteManga(@RequestParam(required = false) String mangaID,@RequestParam(required = false) String author){
        if (mangaID != null && mangaID != "") mangaDB.deleteByMangaID(mangaID);
        else if (author !=null && author != "") authorDB.deleteByAuthorName(author);
        return "redirect:/";
    }

}
