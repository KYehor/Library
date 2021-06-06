package com.library.repositories;

import com.library.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
    @Query(value="SELECT * FROM Category where category.name like %:keyword%", nativeQuery = true)
    List<Category> findByKeyword(@Param("keyword")String name);
}
