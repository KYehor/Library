package com.library.repositories;

import com.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByCategoryId(Long id);
    @Query(value="SELECT * FROM  book left join category on book.category_id = category.id where book.book_id like %:keyword% " +
            "or book.name like %:keyword% or category.name like %:keyword% or book.author like %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword")String keyword);
}
