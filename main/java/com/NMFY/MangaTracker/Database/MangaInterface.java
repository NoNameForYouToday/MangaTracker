package com.NMFY.MangaTracker.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MangaInterface extends JpaRepository<Manga,Long> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Manga m WHERE m.mangaID = :mangaID")
    void deleteByMangaID(@Param("mangaID") String mangaID);

    @Modifying
    @Transactional
    @Query("UPDATE Manga m set m.title = :title WHERE m.mangaID = :mangaID")
    void updateMangaTitleViaID(@Param("mangaID") String mangaID,@Param("title")String title);
}
