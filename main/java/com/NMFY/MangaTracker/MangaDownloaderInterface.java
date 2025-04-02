package com.NMFY.MangaTracker;

import com.NMFY.MangaTracker.Database.Author;
import com.NMFY.MangaTracker.Database.AuthorInterface;
import com.NMFY.MangaTracker.Database.Manga;
import com.NMFY.MangaTracker.Database.MangaInterface;
import com.NMFY.MangaTracker.util.DiscordWebHook;
import downloader.Downloader.MangaDex;
import downloader.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MangaDownloaderInterface {
    @Autowired
    private MangaInterface mangaDB;

    @Autowired
    private AuthorInterface authorDB;

    @Scheduled(initialDelay = 7000,fixedDelay =1200000 )
    public  void checkDatabase(){

        List<Manga> mangas = mangaDB.findAll();
        List<Author> authors = authorDB.findAll();


        Main.debug("Checking");

        //Author checking
        for (Author author:authors){
            MangaDex dex = new MangaDex();
            ArrayList<String> mangaIDs = dex.getMangaIDsFromAuthor(author.getName());


                authorDB.updateMangaCountViaName(author.getName(), (long) mangaIDs.size());

             for (String mangaID:mangaIDs){
                if (mangaDB.exitsByMangaID(mangaID)){
                    Main.debug("Skipping as mangaid already exits");
                    continue;
                }else{
                    try {
                    DiscordWebHook.sendRequest("@everyone \\n"+
                            "Added a new manga from Author: "+author.getName()+"\\n"+
                            "Title: "+dex.getTitle(mangaID));
                        Manga manga = new Manga(mangaID,author.outDir,author.mode,author.language);
                        mangaDB.save(manga);
                    } catch (Exception e) {
                        Main.debug("ERROR: PROBABLY UNABLE TO RETRIEVE SKIPPING: "+mangaID);
                    }

                    DiscordWebHook.sendRequest("Finished fetching for: " );
                }
            }
        }

        //Manga checking author checking
        for (Manga manga: mangas){
            try {


                String args[] = new String[]{"-i", manga.getMangaID(), "-o", manga.getOutDir(), "-m", manga.getMode()};
                MangaDex dex = new MangaDex(args);

                String title = dex.getTitle().replace("\"","");
                mangaDB.updateMangaTitleViaID(manga.getMangaID(),title);
                float vals[] = dex.getHighestChapterAndVolume();
                Main.debug(title);
                if (manga.getHighestVolume() != vals[0] && manga.getHighestChapter() != vals[1]) {

                    DiscordWebHook.sendRequest("@everyone \\n"+
                            "Found new release for: " + title+"\\n" +
                            "Volume: "+manga.getHighestVolume()+"->"+String.valueOf(vals[0])+"\\n" +
                            "Chapter: "+manga.getHighestChapter()+"->"+vals[1]);
                    dex.downloadManga();
                    DiscordWebHook.sendRequest("Finished fetching for: " + title);
                }

                mangaDB.updateMangaHighestVolumeViaID(manga.getMangaID(), vals[0]);
                mangaDB.updateMangaHighestChapterViaID(manga.getMangaID(), vals[1]);
            } catch (Exception e) {
                DiscordWebHook.sendRequest("ERROR:"+e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
