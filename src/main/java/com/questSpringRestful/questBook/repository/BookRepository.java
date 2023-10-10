package com.questSpringRestful.questBook.repository;

import com.questSpringRestful.questBook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingOrContentContaining(String searchTerm, String searchTerm1);
}