package ru.isshepelev.videocdlibrary.infrastructure.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
