package com.NMFY.MangaTracker.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorInterface extends JpaRepository<Author,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Author a WHERE a.name = :name")
    void deleteByAuthorName(@Param("name") String name);


    @Transactional
    @Query("SELECT COUNT(a) > 0 FROM Author a WHERE a.name = :name")
    boolean exitsByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("UPDATE Author a set a.numberOfMangas = :numberOfMangas WHERE a.name = :name")
    void updateMangaCountViaName(@Param("name") String name, @Param("numberOfMangas")Long numberOfMangas);
}
