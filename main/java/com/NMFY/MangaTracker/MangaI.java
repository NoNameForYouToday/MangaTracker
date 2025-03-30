package com.NMFY.MangaTracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MangaI extends JpaRepository<Manga,Long> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Manga m WHERE m.mangaID = :mangaID")
    void deleteByMangaID(@Param("mangaID") String mangaID);
}
