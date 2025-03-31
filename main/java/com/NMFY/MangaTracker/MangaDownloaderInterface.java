package com.NMFY.MangaTracker;

import com.NMFY.MangaTracker.Database.Manga;
import com.NMFY.MangaTracker.Database.MangaInterface;
import com.NMFY.MangaTracker.util.DiscordWebHook;
import downloader.Downloader.MangaDex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaDownloaderInterface {
    @Autowired
    private MangaInterface mangaDB;


    @Scheduled(fixedRate = 900000)
    public void checkDatabase(){

        List<Manga> mangas = mangaDB.findAll();
        for (Manga manga: mangas){
            try {


                String args[] = new String[]{"-i", manga.getMangaID(), "-o", manga.getOutDir(), "-m", "volume"};
                MangaDex dex = new MangaDex(args);

                float vals[] = dex.getHighestChapterAndVolume();

                if (manga.getHighestVolume() != vals[0] && manga.getHighestChapter() != vals[1]) {
                    DiscordWebHook.sendRequest("Found new release for: " + manga.getId());
                    dex.downloadManga();
                    DiscordWebHook.sendRequest("Finished fetching for: " + manga.getId());
                }
            } catch (Exception e) {
                DiscordWebHook.sendRequest(e.getMessage());
            }
        }
    }

}
