package ru.isshepelev.videocdlibrary.infrastructure.persistance.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Category;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Video;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Video> findCategoriesById(Long id);


    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name%")
    List<Category> findCategoriesByNameContaining(@Param("name") String name);
}
